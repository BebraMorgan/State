package ru.totalcraftmc.statesplugin.commands.subcommands.player;

import org.bukkit.entity.Player;
import ru.totalcraftmc.statesplugin.commands.utils.PlayerRequired;
import ru.totalcraftmc.statesplugin.commands.utils.SubCommand;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;

import java.lang.reflect.InvocationTargetException;

public class Info implements SubCommand, PlayerRequired {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private Player player;

    @Override
    public void execute(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String name;
        if (args.length < 2) name = player.getName();
        else name = args[1];

        StatePlayer statePlayer = playerDAO.findByName(name);
        if (statePlayer == null) {
            player.sendMessage("Игрок не найден");
            return;
        }
        if (statePlayer.cant("destroyCity", StatePlayer.class)) {
            player.sendMessage("Вы не можете уничтожить город");
        }
        StringBuilder info = new StringBuilder();
        Balance balance = statePlayer.getBalance();
        info.append("Игрок: ").append(statePlayer.getName()).append("\n").append("Город: ");

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

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return "info";
    }
}
