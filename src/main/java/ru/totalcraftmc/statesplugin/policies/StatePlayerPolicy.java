package ru.totalcraftmc.statesplugin.policies;

import ru.totalcraftmc.statesplugin.entities.StatePlayer;
import ru.totalcraftmc.statesplugin.policies.utils.Policy;

public class StatePlayerPolicy extends Policy {
    public boolean createCity(StatePlayer player) {
        return player.notResident();
    }

    public boolean destroyCity(StatePlayer player) {
        return player.isMayor();
    }

    public boolean renameCity(StatePlayer player) {
        return player.isMayor() || player.isAssistant();
    }

    public boolean assistantManage(StatePlayer player) {
        return player.isMayor();
    }

    public boolean kickFromCity(StatePlayer player) {
        return player.isMayor() || player.isAssistant();
    }

    public boolean inviteToCity(StatePlayer player) {
        return player.isMayor() || player.isAssistant();
    }

    public boolean destroyState(StatePlayer player) {
        return player.isStateLeader();
    }

    public boolean ministerManage(StatePlayer player) {
        return player.isStateLeader();
    }

    public boolean inviteToState(StatePlayer player) {
        return player.isStateLeader() || player.isMinister();
    }

    public boolean createState(StatePlayer player) {
        return player.isMayor() || player.getState() == null;
    }
}
