package ru.totalcraftmc.statesplugin.commands.subcommands.alliance;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.alliance.AllianceLeaveEvent;

public class Leave implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        callEvent(new AllianceLeaveEvent(player));
    }

    @Override
    public String getName() {
        return "leader";
    }
}
