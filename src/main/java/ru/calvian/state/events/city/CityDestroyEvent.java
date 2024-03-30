package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.entities.City;
import ru.calvian.state.events.AbstractEvent;

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
