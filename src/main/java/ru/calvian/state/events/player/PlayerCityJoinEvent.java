package ru.calvian.state.events.player;

import lombok.Getter;
import ru.calvian.state.entities.City;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.events.AbstractEvent;
@Getter
public class PlayerCityJoinEvent extends AbstractEvent {
    private final City city;
    private final StatePlayer player;
    public PlayerCityJoinEvent(City city, StatePlayer player) {
        this.city = city;
        this.player = player;
    }
}
