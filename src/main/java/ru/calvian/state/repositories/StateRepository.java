package ru.calvian.state.repositories;


import ru.calvian.state.entities.City;
import ru.calvian.state.entities.State;

import java.util.List;

public class StateRepository extends BaseRepository<State> {
    public StateRepository() {
        super(new State());
    }

    public List<State> findByName(String name) {
        return (List<State>) entityManager.createQuery("SELECT p FROM State p WHERE name = '" + name + "'").getResultList();
    }
}
