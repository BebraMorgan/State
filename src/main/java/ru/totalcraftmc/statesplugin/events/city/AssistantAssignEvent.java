package ru.totalcraftmc.statesplugin.events.city;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.events.utils.AbstractEvent;

@Getter
public class AssistantAssignEvent extends AbstractEvent {
    private final Player player;
    private final String name;

    public AssistantAssignEvent(Player player, String name) {
        this.name = name;
        this.player = player;
    }
}
