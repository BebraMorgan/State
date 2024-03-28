package ru.calvian.state.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.player.PlayerCityLeaveEvent;
import ru.calvian.state.repositories.CityRepository;
import ru.calvian.state.repositories.StatePlayerRepository;

public class PlayerCityLeaveListener implements Listener {
    private final StatePlayerRepository playerRepository = new StatePlayerRepository();
    private final CityRepository cityRepository = new CityRepository();
    @EventHandler
    public void onCityJoin(PlayerCityLeaveEvent event) {
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
        player.getPlayer().sendMessage(String.format(Messages.CITY_LEFT, city.getName()));
    }
}
