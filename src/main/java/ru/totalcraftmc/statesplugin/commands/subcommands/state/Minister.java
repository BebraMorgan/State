package ru.totalcraftmc.statesplugin.commands.subcommands.state;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.state.MinisterAssignEvent;
import ru.totalcraftmc.statesplugin.events.state.MinisterDismissEvent;

public class Minister implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return "minister";
    }

    @Override
    public void execute(String[] args) {
        switch (args[1]) {
            case "assign" -> callEvent(new MinisterAssignEvent(args[2], player));
            case "dismiss" -> callEvent(new MinisterDismissEvent(args[2], player));
        }
    }
}
