package ru.calvian.state.repositories;

import ru.calvian.state.entities.StatePlayer;

import java.util.List;

public class StatePlayerRepository extends BaseRepository<StatePlayer> {
    public StatePlayerRepository() {
        super(new StatePlayer());
    }

    public List<StatePlayer> findByNick(String nick) {
        return (List<StatePlayer>) entityManager.createQuery("SELECT p FROM StatePlayer p WHERE nick = '" + nick + "'").getResultList();
    }
}
