package ru.calvian.state.repositories;

import ru.calvian.state.entities.invites.CityInvite;

import java.util.List;

public class CityInviteRepository extends BaseRepository<CityInvite> {
    public CityInviteRepository() {
        super(new CityInvite());
    }

    public List<CityInvite> findByCity(int cityId) {
        return (List<CityInvite>) entityManager.createQuery("SELECT p FROM CityInvite p WHERE city_id = '" + cityId + "'").getResultList();
    }
    public List<CityInvite> findByPlayer(int playerId) {
        return (List<CityInvite>) entityManager.createQuery("SELECT p FROM CityInvite p WHERE player_id = '" + playerId + "'").getResultList();
    }
}
