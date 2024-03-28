package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class CityCreateEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public CityCreateEvent(String name, Player player) {
        this.player = player;
        this.name = name;
    }
}
