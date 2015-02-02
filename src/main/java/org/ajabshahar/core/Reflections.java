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

    public List<Reflection> getAll(Boolean showOnLandingPage) {

        return reflectionRepository.findAll(showOnLandingPage);
    }
}
