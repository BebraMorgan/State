package ru.totalcraftmc.statesplugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.totalcraftmc.statesplugin.dao.BalanceDAO;
import ru.totalcraftmc.statesplugin.dao.PlayerDAO;
import ru.totalcraftmc.statesplugin.entities.Balance;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;

public class PlayerListener implements Listener {

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final BalanceDAO balanceDAO = new BalanceDAO();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        StatePlayer statePlayer = playerDAO.findByName(player.getName());
        if (statePlayer == null) {
            statePlayer = new StatePlayer();
            statePlayer.setName(player.getName());
            statePlayer.setCity(null);
            Balance balance = new Balance();
            balanceDAO.insert(balance);
            statePlayer.setBalance(balance);
            playerDAO.insert(statePlayer);
        }
    }

}
