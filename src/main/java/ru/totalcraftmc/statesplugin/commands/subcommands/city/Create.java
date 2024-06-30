package ru.totalcraftmc.statesplugin.commands.subcommands.city;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.city.CityCreateEvent;

public class Create implements SubCommand, PlayerRequired, CallsEvents {
    private Player player;

    @Override
    public void execute(String[] args) {
        callEvent(new CityCreateEvent(args[1], player));
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
