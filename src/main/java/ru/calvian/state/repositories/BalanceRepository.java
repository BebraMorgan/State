package ru.calvian.state.repositories;

import ru.calvian.state.entities.Balance;

public class BalanceRepository extends BaseRepository<Balance> {
    public BalanceRepository() {
        super(new Balance());
    }
}
