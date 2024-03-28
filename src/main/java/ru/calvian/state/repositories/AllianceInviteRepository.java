package ru.calvian.state.repositories;

import ru.calvian.state.entities.invites.AllianceInvite;
import ru.calvian.state.entities.invites.CityInvite;

import java.util.List;

public class AllianceInviteRepository extends BaseRepository<AllianceInvite> {
    public AllianceInviteRepository() {
        super(new AllianceInvite());
    }

    public List<AllianceInvite> findByState(int stateId) {
        return (List<AllianceInvite>) entityManager.createQuery("SELECT p FROM AllianceInvite p WHERE state_id = '" + stateId + "'").getResultList();
    }
    public List<AllianceInvite> findByAlliance(int allianceId) {
        return (List<AllianceInvite>) entityManager.createQuery("SELECT p FROM AllianceInvite p WHERE alliance_id = '" + allianceId + "'").getResultList();
    }
}
