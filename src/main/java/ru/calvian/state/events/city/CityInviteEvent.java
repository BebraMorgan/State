package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class CityInviteEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public CityInviteEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
