package ru.calvian.state.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.invites.CityInvite;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.entities.dictionaries.DictPlayerRoles;
import ru.calvian.state.repositories.CityInviteRepository;
import ru.calvian.state.repositories.CityRepository;
import ru.calvian.state.repositories.DictPlayerRolesRepository;
import ru.calvian.state.repositories.StatePlayerRepository;

import java.util.List;

public class PlayerJoinListener implements Listener {
    StatePlayerRepository repository = new StatePlayerRepository();
    DictPlayerRolesRepository dictPlayerRolesRepository = new DictPlayerRolesRepository();
    CityRepository cityRepository = new CityRepository();
    CityInviteRepository cityInviteRepository = new CityInviteRepository();
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DictPlayerRoles role = dictPlayerRolesRepository.find(1);
        Player player = event.getPlayer();
        List<StatePlayer> playerList = repository.findByNick(player.getName());
        StatePlayer statePlayer = new StatePlayer();
        if (playerList.isEmpty()) {
            statePlayer.setNick(player.getName());
            statePlayer.setRole(role);
            statePlayer.setBalance(new Balance());
            repository.insert(statePlayer);
        } else statePlayer = playerList.get(0);
        List<CityInvite> invites = cityInviteRepository.findByPlayer(statePlayer.getId());
        if (invites.isEmpty()) return;
        invites.forEach(invite -> {
            event.getPlayer().sendMessage("У вас есть приглашение в город " + invite.getCity().getName());
        });

    }
}
