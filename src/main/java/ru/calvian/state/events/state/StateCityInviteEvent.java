package ru.calvian.state.events.state;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ru.calvian.state.events.AbstractEvent;
@Getter
public class StateCityInviteEvent extends AbstractEvent {
    private final String name;
    private final Player player;
    public StateCityInviteEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
