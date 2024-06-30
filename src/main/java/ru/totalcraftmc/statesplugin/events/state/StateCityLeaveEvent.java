package ru.totalcraftmc.statesplugin.events.state;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class StateCityLeaveEvent extends AbstractEvent {
    private final Player player;

    public StateCityLeaveEvent(Player player) {
        this.player = player;
    }
}