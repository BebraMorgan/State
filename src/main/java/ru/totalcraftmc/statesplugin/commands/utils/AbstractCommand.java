package ru.totalcraftmc.statesplugin.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.totalcraftmc.statesplugin.StatesPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class AbstractCommand implements CommandExecutor, ExecuteModified {


    protected Map<String, SubCommand> commands = new HashMap<>();

    public AbstractCommand(String command, Stream<SubCommand> commandsStream) {
        PluginCommand pluginCommand = StatesPlugin.getInstance().getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
        commandsStream.forEach(subCommand -> commands.put(subCommand.getName(), subCommand));
    }

    public void execute(CommandSender sender, String[] args) {
        beforeExecute();
        if (args.length < 1) return;
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.ONLY_PLAYERS);
            return;
        }
        SubCommand command = commands.get(args[0]);
        if (command instanceof PlayerRequired pCommand) pCommand.setPlayer(player);
        afterExecute();
        command.execute(args);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        execute(sender, args);
        return true;
    }


}
