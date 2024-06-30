package ru.totalcraftmc.statesplugin.commands.subcommands.city;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.player.PlayerCityLeaveEvent;

public class Leave implements SubCommand, PlayerRequired, CallsEvents {
    public Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) return;
        callEvent(new PlayerCityLeaveEvent(player));
    }

    @Override
    public String getName() {
        return "leave";
    }
}
