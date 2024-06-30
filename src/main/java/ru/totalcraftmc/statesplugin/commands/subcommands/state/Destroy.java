package ru.totalcraftmc.statesplugin.commands.subcommands.state;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.state.StateDestroyEvent;

public class Destroy implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return "destroy";
    }

    @Override
    public void execute(String[] args) {
        callEvent(new StateDestroyEvent(player));
    }
}
