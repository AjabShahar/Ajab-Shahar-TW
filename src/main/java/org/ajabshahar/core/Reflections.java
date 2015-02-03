package org.ajabshahar.core;

import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.Reflection;

import java.util.List;

public class Reflections {

    private final ReflectionDAO reflectionRepository;

    public Reflections(ReflectionDAO reflectionRepository) {
        this.reflectionRepository = reflectionRepository;
    }

    public Reflection create(Reflection reflection) {
        return reflectionRepository.create(reflection);
    }

    public List<Reflection> getAll(String criteria) {

        boolean landingPage = false, authoringComplete = false;
        if (criteria.equalsIgnoreCase("featured")) {
            landingPage = true;
        } else if (criteria.equalsIgnoreCase("authoringComplete")) {
            landingPage = true;
            authoringComplete = true;
        }
        return reflectionRepository.findAll(landingPage, authoringComplete);
    }

    public List<Reflection> findAll() {

        return getAll("");
    }
}
