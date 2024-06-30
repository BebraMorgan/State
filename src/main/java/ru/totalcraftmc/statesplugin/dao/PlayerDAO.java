package ru.totalcraftmc.statesplugin.dao;

import ru.totalcraftmc.statesplugin.dao.utils.BaseDAO;
import ru.totalcraftmc.statesplugin.entities.StatePlayer;

public class PlayerDAO extends BaseDAO<StatePlayer> {
    public StatePlayer findByName(String name) {
        try {
            return (StatePlayer) entityManager.createQuery("SELECT p FROM StatePlayer p WHERE name = '" + name + "'").getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
