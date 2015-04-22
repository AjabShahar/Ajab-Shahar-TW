package org.ajabshahar.core;

import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.Reflection;

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
        } else if (criteria.equalsIgnoreCase("authoringComplete")) {
            landingPage = true;
            authoringComplete = true;
        }
        return reflectionRepository.findBy(landingPage, authoringComplete);
    }


    public Reflection findReflection(int id) {
        return reflectionRepository.find(id).iterator().next();
    }

    public Set<Reflection> findBy(int startFrom, String filteredLetter) {
        return reflectionRepository.findBy(startFrom, filteredLetter);
    }
}
