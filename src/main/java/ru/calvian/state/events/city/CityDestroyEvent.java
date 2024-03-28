package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class CityDestroyEvent extends AbstractEvent {
    private final Player player;

    public CityDestroyEvent(Player player) {
        this.player = player;
    }
}
