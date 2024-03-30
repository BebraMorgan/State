package ru.calvian.state.commands.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.repositories.StatePlayerRepository;

public abstract class PlayerCommand extends AbstractCommand {
    protected Player player;
    protected String[] args;
    protected StatePlayer statePlayer;
    protected final StatePlayerRepository playerRepository = new StatePlayerRepository();

    public PlayerCommand(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player mcPlayer)) {
            sender.sendMessage(Messages.ONLY_PLAYERS);
            return;
        }
        this.args = args;
        player = mcPlayer;
        statePlayer = playerRepository.findByNick(player.getName()).get(0);
        performExecute();
    }

    public abstract void performExecute();
}
