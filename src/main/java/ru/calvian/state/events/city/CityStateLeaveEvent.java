package ru.calvian.state.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.AbstractEvent;
@Getter
public class CityStateLeaveEvent extends AbstractEvent {
    private final StatePlayer player;

    public CityStateLeaveEvent(StatePlayer player) {
        this.player = player;
    }
}
