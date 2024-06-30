package ru.totalcraftmc.statesplugin.events.player;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class PlayerCityLeaveEvent extends AbstractEvent {
    private final Player player;

    public PlayerCityLeaveEvent(Player player) {
        this.player = player;
    }
}
