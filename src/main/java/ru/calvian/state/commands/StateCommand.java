package ru.calvian.state.commands;

import org.bukkit.Bukkit;
import ru.calvian.state.commands.utils.BalanceCommand;
import ru.calvian.state.commands.utils.Messages;
import ru.calvian.state.commands.utils.PlayerCommand;
import ru.calvian.state.entities.Balance;
import ru.calvian.state.entities.State;
import ru.calvian.state.events.city.CityStateJoinEvent;
import ru.calvian.state.events.city.CityStateLeaveEvent;
import ru.calvian.state.events.state.*;
import ru.calvian.state.repositories.StateRepository;

import java.util.List;

public class StateCommand extends PlayerCommand {
    public StateCommand() {
        super("state");
    }


    private final StateRepository stateRepository = new StateRepository();


    @Override
    public void performExecute() {
        if (args.length == 0) return;
        switch (args[0]) {
            case "create" -> create();
            case "destroy" -> destroy();
            case "bank", "b" -> new BalanceCommand(player, statePlayer.getCity().getState().getBalance(), args).bank();
            case "rename" -> rename();
            case "leader" -> leader();
            case "join" -> join();
            case "leave" -> leave();
            case "invite" -> invite();
            case "kick" -> kick();
            case "ideology" -> ideology();
            case "info", "i" ->
                    info(args[1] == null && statePlayer.getState() != null ? statePlayer.getState().getName() : args[1]);
            case "minister" -> minister();
        }
    }

    public void info(String name) {
        List<State> states = stateRepository.findByName(name);
        if (states.isEmpty()) {
            player.sendMessage(Messages.STATE_NOT_FOUND);
        }
        State state = states.get(0);
        StringBuilder info = new StringBuilder();
        info.append("Государство: ").append(state.getName()).append("\n").append("Мэр: ").append(state.getLeader().getNick()).append("\n").append("Министры: ").append("\n");
        state.getMinisters().forEach(minister -> info.append(minister.getNick()).append(","));
        info.append("\n").append("Города: ").append("\n");
        state.getCities().forEach(city -> info.append(city.getName()).append(','));
        Balance balance = state.getBalance();
        info.append("Баланс:\nЖелезо: ").append(balance.getIron()).append("\n").append("Алмазы: ").append(balance.getDiamonds()).append("\n").append("Незерит: ").append(balance.getNetherite()).append("\n");
        player.sendMessage(info.toString());
    }

    private void minister() {
        if (statePlayer.getState() == null) {
            player.sendMessage(Messages.NO_STATE);
            return;
        }
        if (args.length == 1) {
            player.sendMessage("Министры в вашем государстве: ");
            statePlayer.getState().getMinisters().forEach(minister -> player.sendMessage(minister.getNick() + ","));
            return;
        }
        if (args[2] == null) return;
        switch (args[1]) {
            case "assign" -> Bukkit.getPluginManager().callEvent(new MinisterAssignEvent(args[2], player));
            case "dismiss" -> Bukkit.getPluginManager().callEvent(new MinisterDismissEvent(args[2], player));
        }
    }

    private void join() {
        Bukkit.getPluginManager().callEvent(new CityStateJoinEvent(args[1], player));
    }

    private void leave() {
        Bukkit.getPluginManager().callEvent(new CityStateLeaveEvent(statePlayer));
    }

    private void kick() {
        Bukkit.getPluginManager().callEvent(new StateCityKickEvent(args[1], player));
    }

    private void invite() {
        Bukkit.getPluginManager().callEvent(new StateCityInviteEvent(args[1], player));
    }

    private void create() {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new StateCreateEvent(args[1], player));
    }

    private void destroy() {
        Bukkit.getPluginManager().callEvent(new StateDestroyEvent(statePlayer));
    }


    private void rename() {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new StateRenameEvent(args[1], player));
    }

    private void leader() {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new LeaderSetEvent(args[1], player));
    }

    private void ideology() {
        if (args.length < 2) return;
        Bukkit.getPluginManager().callEvent(new StateIdeologySetEvent(args[1], player));
    }


}
