package ru.calvian.state.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.entities.dictionaries.DictPlayerRoles;
import ru.calvian.state.entities.invites.CityInvite;
import ru.calvian.state.events.city.CityDestroyEvent;
import ru.calvian.state.events.player.PlayerCityJoinEvent;
import ru.calvian.state.events.player.PlayerCityLeaveEvent;
import ru.calvian.state.repositories.*;

import java.util.List;


public class PlayerListener implements Listener {
    private final CityInviteRepository inviteRepository = new CityInviteRepository();
    private final StatePlayerRepository playerRepository = new StatePlayerRepository();
    private final CityRepository cityRepository = new CityRepository();
    DictPlayerRolesRepository dictPlayerRolesRepository = new DictPlayerRolesRepository();
    BalanceRepository balanceRepository = new BalanceRepository();

    @EventHandler
    public void onCityJoin(PlayerCityJoinEvent event) {
        StatePlayer player = event.getPlayer();
        City city = event.getCity();
        if (city == null) {
            player.getPlayer().sendMessage(Messages.CITY_NOT_FOUND);
            return;
        }

        if (player.getCity() != null) {
            player.getPlayer().sendMessage(String.format(Messages.ALREADY_IN_CITY, player.getCity().getName()));
            return;
        }

        if (inviteRepository.findByCity(event.getCity().getId()).isEmpty()) {
            player.getPlayer().sendMessage(String.format(Messages.CITY_NOT_INVITED, city.getName()));
            return;
        }
        player.setCity(city);
        city.getPlayers().add(player);
        inviteRepository.findByPlayer(player.getId()).forEach(inviteRepository::delete);
        playerRepository.update(player);
        cityRepository.update(city);
        player.getPlayer().sendMessage(String.format(Messages.CITY_JOINED, city.getName()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DictPlayerRoles role = dictPlayerRolesRepository.find(1);
        Player player = event.getPlayer();
        List<StatePlayer> playerList = playerRepository.findByNick(player.getName());
        StatePlayer statePlayer = new StatePlayer();
        if (playerList.isEmpty()) {
            statePlayer.setNick(player.getName());
            statePlayer.setRole(role);
            Balance balance = new Balance();
            balanceRepository.insert(balance);
            statePlayer.setBalance(balance);
            playerRepository.insert(statePlayer);
        } else statePlayer = playerList.get(0);
        List<CityInvite> invites = inviteRepository.findByPlayer(statePlayer.getId());
        if (invites.isEmpty()) return;
        invites.forEach(invite -> event.getPlayer().sendMessage("У вас есть приглашение в город " + invite.getCity().getName()));

    }

    @EventHandler
    public void onCityLeave(PlayerCityLeaveEvent event) {
        StatePlayer player = event.getStatePlayer();
        City city = player.getCity();
        if (city == null) {
            player.getPlayer().sendMessage(Messages.NO_CITY);
            return;
        }
        city.getPlayers().remove(player);
        player.setCity(null);
        playerRepository.update(player);
        cityRepository.update(city);
        if(city.getPlayers().isEmpty()) {
            Bukkit.getPluginManager().callEvent(new CityDestroyEvent(city));
        }
        player.getPlayer().sendMessage(String.format(Messages.CITY_LEFT, city.getName()));
    }
}
