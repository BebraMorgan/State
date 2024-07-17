package ru.totalcraftmc.statesplugin.events.alliance;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

public class AllianceInviteEvent extends AbstractEvent {
    private final String name;
    private final Player player;

    public AllianceInviteEvent(String name, Player player) {
        this.name = name;
        this.player = player;
    }
}
