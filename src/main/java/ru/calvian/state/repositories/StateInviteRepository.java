package ru.calvian.state.repositories;

import ru.calvian.state.entities.invites.CityInvite;
import ru.calvian.state.entities.invites.StateInvite;

import java.util.List;

public class StateInviteRepository extends BaseRepository<StateInvite> {
    public StateInviteRepository() {
        super(new StateInvite());
    }

    public List<StateInvite> findByCity(int cityId) {
        return (List<StateInvite>) entityManager.createQuery("SELECT p FROM StateInvite p WHERE city_id = '" + cityId + "'").getResultList();
    }
    public List<StateInvite> findByState(int stateId) {
        return (List<StateInvite>) entityManager.createQuery("SELECT p FROM StateInvite p WHERE player_id = '" + stateId + "'").getResultList();
    }
}
