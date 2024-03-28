package ru.calvian.state.commands;

import ru.calvian.state.commands.utils.BalanceCommand;
import ru.calvian.state.commands.utils.PlayerCommand;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.StatePlayer;
import ru.calvian.state.repositories.StatePlayerRepository;

import java.util.List;

public class StatePlayerCommand extends PlayerCommand {
    private final StatePlayerRepository playerRepository = new StatePlayerRepository();

    public StatePlayerCommand() {
        super("player");
    }

    @Override
    public void performExecute(String[] args) {
        StatePlayer statePlayer = playerRepository.findByNick(player.getName()).get(0);
        switch (args[0]) {
            case "info" -> info(args[1] != null ? args[1] : player.getName());
            case "bank" -> new BalanceCommand(player, statePlayer.getBalance(), args).bank();
            default -> info(player.getName());
        }
    }

    private void info(String nick) {
        List<StatePlayer> statePlayers = playerRepository.findByNick(nick);
        if (statePlayers.isEmpty()) {
            player.sendMessage("Игрок не найден");
            return;
        }
        StringBuilder info = new StringBuilder();
        StatePlayer statePlayer = statePlayers.get(0);
        Balance balance = statePlayer.getBalance();

        info.append("Игрок: ").append(statePlayer.getNick()).append("\n").append("Город: ");

        if (statePlayer.getCity() != null) {
            info.append(statePlayer.getCity().getName());
        } else {
            info.append("Нет");
        }

        info.append("\n").append("Государство: ");
        if (statePlayer.getState() != null) {
            info.append(statePlayer.getState().getName());
        } else {
            info.append("Нет");
        }

        info.append("\n").append("Альянс: ");
        if (statePlayer.getAlliance() != null) {
            info.append(statePlayer.getAlliance().getName());
        } else {
            info.append("Нет");
        }

        info.append("\n").append("Баланс:").append("\n")
                .append("Железо: ").append(balance.getIron()).append("\n")
                .append("Алмазы: ").append(balance.getDiamonds()).append("\n")
                .append("Незерит: ").append(balance.getNetherite()).append("\n");
        player.sendMessage(info.toString());
    }
}

