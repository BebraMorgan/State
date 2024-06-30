package ru.totalcraftmc.statesplugin.commands.subcommands.city;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.Messages;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;
import ru.totalcraftmc.statesplugin.events.city.MayorSetEvent;

public class Mayor implements SubCommand, CallsEvents, PlayerRequired {

    private final PlayerDAO playerDAO = new PlayerDAO();
    private Player player;

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String[] args) {
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer.getCity() == null) {
            player.sendMessage(Messages.NO_CITY);
            return;
        }
        if (args.length == 1) {
            player.sendMessage("Мэр вашего города: " + statePlayer.getCity().getMayor().getName());
            return;
        }
        if (args.length < 3) return;
        if (args[1].equals("assign")) {
            callEvent(new MayorSetEvent(player, args[2]));
        }
    }
}
