package ru.totalcraftmc.statesplugin.commands.subcommands.city;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.CallsEvents;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;
import ru.totalcraftmc.statesplugin.events.city.AssistantAssignEvent;
import ru.totalcraftmc.statesplugin.events.city.AssistantDismissEvent;

public class Assistant implements SubCommand, PlayerRequired, CallsEvents {
    private final PlayerDAO playerDAO = new PlayerDAO();
    public Player player;

    @Override
    public void execute(String[] args) {
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (args.length == 1) {
            if (statePlayer.getCity().getAssistants().isEmpty()) {
                player.sendMessage("Ассистентов в вашем городе нет");
                return;
            }
            player.sendMessage("Ассистенты в вашем городе: ");
            statePlayer.getCity().getAssistants().forEach(assistant -> player.sendMessage(assistant.getName() + ","));
            return;
        }
        if (args.length == 2) return;
        switch (args[1]) {
            case "assign" -> callEvent(new AssistantAssignEvent(player, args[2]));
            case "dismiss" -> callEvent(new AssistantDismissEvent(player, args[2]));
        }
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
