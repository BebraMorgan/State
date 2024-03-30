package ru.calvian.state.events.state;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;
@Getter
public class StateCreateEvent extends AbstractEvent {

    private final String name;
    private final Player player;
    public StateCreateEvent(String name, Player player) {
        this.player = player;
        this.name = name;
    }
}
