package ru.calvian.state.events.state;

import org.bukkit.event.Event;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.AbstractEvent;

public class StateDestroyEvent extends AbstractEvent {
    public final StatePlayer player;
    public StateDestroyEvent(StatePlayer player) {
        this.player = player;
    }
}
