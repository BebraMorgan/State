package ru.calvian.state.events.player;

import lombok.Getter;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.AbstractEvent;

@Getter
public class PlayerCityLeaveEvent extends AbstractEvent {
    private final StatePlayer statePlayer;

    public PlayerCityLeaveEvent(StatePlayer statePlayer) {
        this.statePlayer = statePlayer;
    }
}
