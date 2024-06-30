package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class AssistantDismissEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public AssistantDismissEvent(Player player, String name) {
        this.name = name;
        this.player = player;
    }
}
