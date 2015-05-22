package org.ajabshahar.core;

import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.Reflection;

import java.util.List;
import java.util.Set;

public class Reflections {

    private final ReflectionDAO reflectionRepository;

    public Reflections(ReflectionDAO reflectionRepository) {
        this.reflectionRepository = reflectionRepository;
    }

    public Reflection create(Reflection reflection) {
        return reflectionRepository.create(reflection);
    }

    public Set<Reflection> getAll(String criteria) {

        boolean landingPage = false, authoringComplete = false;
        if (criteria.equalsIgnoreCase("featured")) {
            landingPage = true;
            authoringComplete = true;
        } else if (criteria.equalsIgnoreCase("authoringComplete")) {
            authoringComplete = true;
        }
        return reflectionRepository.findBy(landingPage, authoringComplete);
    }

    public Set<Reflection> getAllByIds(List<Long> ids) {
        return reflectionRepository.findByIds(ids);
    }

    public Reflection findReflection(int id) {
        return reflectionRepository.find(id);
    }

}
