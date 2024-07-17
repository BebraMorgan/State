package ru.totalcraftmc.statesplugin.commands.subcommands.alliance;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.alliance.AllianceInviteEvent;

public class Invite implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) return;
        callEvent(new AllianceInviteEvent(args[1], player));
    }

    @Override
    public String getName() {
        return "invite";
    }
}
