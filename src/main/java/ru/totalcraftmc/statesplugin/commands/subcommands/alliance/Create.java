package ru.totalcraftmc.statesplugin.commands.subcommands.alliance;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.alliance.AllianceCreateEvent;

public class Create implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        callEvent(new AllianceCreateEvent(args[1], player));
    }

    @Override
    public String getName() {
        return "create";
    }
}
