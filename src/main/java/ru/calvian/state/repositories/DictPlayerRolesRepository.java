package ru.calvian.state.repositories;

import ru.calvian.state.entities.dictionaries.DictPlayerRoles;

import java.util.List;

public class DictPlayerRolesRepository extends BaseRepository<DictPlayerRoles> {
    public DictPlayerRolesRepository() {
        super(new DictPlayerRoles());
    }

    public List<DictPlayerRoles> findByDescription(String nick) {
        return (List<DictPlayerRoles>) entityManager.createQuery("SELECT p FROM DictPlayerRoles p WHERE description = '" + nick + "'").getResultList();
    }
}
