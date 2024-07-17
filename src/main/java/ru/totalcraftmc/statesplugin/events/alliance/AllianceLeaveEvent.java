package ru.totalcraftmc.statesplugin.events.alliance;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

public class AllianceLeaveEvent extends AbstractEvent {

    private final Player player;

    public AllianceLeaveEvent( Player player) {
        this.player = player;
    }
}
