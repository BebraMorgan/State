package ru.calvian.state.commands.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends AbstractCommand {
    protected Player player;

    public PlayerCommand(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player mcPlayer)) {
            sender.sendMessage(Messages.ONLY_PLAYERS);
            return;
        }
        player = mcPlayer;
        performExecute(args);
    }

    public abstract void performExecute(String[] args);
}
