package ru.calvian.state.repositories;

import ru.calvian.state.entities.Alliance;

public class AllianceRepository extends BaseRepository<Alliance> {
    public AllianceRepository() {
        super(new Alliance());
    }
}
