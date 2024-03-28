package ru.calvian.state.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.entities.invites.CityInvite;
import ru.calvian.state.events.player.PlayerCityJoinEvent;
import ru.calvian.state.repositories.CityInviteRepository;
import ru.calvian.state.repositories.CityRepository;
import ru.calvian.state.repositories.StatePlayerRepository;


public class PlayerCityJoinListener implements Listener {
    private final CityInviteRepository inviteRepository = new CityInviteRepository();
    private final StatePlayerRepository playerRepository = new StatePlayerRepository();
    private final CityRepository cityRepository = new CityRepository();

    @EventHandler
    public void onCityJoin(PlayerCityJoinEvent event) {
        StatePlayer player = event.getPlayer();
        City city = event.getCity();
        if (city == null) {
            player.getPlayer().sendMessage(Messages.CITY_NOT_FOUND);
            return;
        }
        CityInvite invite = inviteRepository.findByCity(event.getCity().getId()).get(0);
        if (invite == null) {
            player.getPlayer().sendMessage(Messages.CITY_NOT_INVITED);
            return;
        }
        if (player.getCity() != null) {
            player.getPlayer().sendMessage(String.format(Messages.ALREADY_IN_CITY, player.getCity().getName()));
            return;
        }
        player.setCity(city);
        city.getPlayers().add(player);
        inviteRepository.findByPlayer(player.getId()).forEach(inviteRepository::delete);
        playerRepository.update(player);
        cityRepository.update(city);
        player.getPlayer().sendMessage(String.format(Messages.CITY_JOINED, city.getName()));
    }
}
