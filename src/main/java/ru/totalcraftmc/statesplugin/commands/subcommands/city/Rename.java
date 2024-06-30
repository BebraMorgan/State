package ru.totalcraftmc.statesplugin.commands.subcommands.city;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.events.city.CityRenameEvent;

public class Rename implements SubCommand, PlayerRequired, CallsEvents {

    public Player player;

    @Override
    public void execute(String[] args) {
        callEvent(new CityRenameEvent(args[1], player));
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return "rename";
    }
}
