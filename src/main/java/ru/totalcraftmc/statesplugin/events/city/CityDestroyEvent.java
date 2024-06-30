package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.entities.City;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class CityDestroyEvent extends AbstractEvent {
    private final Player player;
    private final City city;

    public CityDestroyEvent(Player player) {
        this.player = player;
        this.city = null;
    }

    public CityDestroyEvent(City city) {
        this.city = city;
        this.player = null;
    }
}
