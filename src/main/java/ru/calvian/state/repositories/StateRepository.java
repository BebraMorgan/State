package ru.calvian.state.repositories;


import ru.calvian.state.entities.State;

public class StateRepository extends BaseRepository<State> {
    public StateRepository() {
        super(new State());
    }
}
