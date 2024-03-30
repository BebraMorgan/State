package ru.calvian.state.events.state;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class StateCityKickEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public StateCityKickEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
