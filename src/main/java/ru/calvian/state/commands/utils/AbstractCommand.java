package ru.calvian.state.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;
import ru.calvian.state.States;

public abstract class AbstractCommand implements CommandExecutor {
    public AbstractCommand(String command) {
        PluginCommand pluginCommand = States.instance.getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        execute(sender, args);
        return true;
    }

}
