package ru.calvian.state.repositories;

import ru.calvian.state.entities.City;

import java.util.List;

public class CityRepository extends BaseRepository<City> {
    public CityRepository() {
        super(new City());
    }

    public List<City> findByName(String name) {
        return (List<City>) entityManager.createQuery("SELECT p FROM City p WHERE name = '" + name + "'").getResultList();
    }
}
