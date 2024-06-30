package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class CityStateLeaveEvent extends AbstractEvent {
    private final StatePlayer player;

    public CityStateLeaveEvent(StatePlayer player) {
        this.player = player;
    }
}
