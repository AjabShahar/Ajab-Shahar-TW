package org.ajabshahar.core;

import org.ajabshahar.platform.daos.ReflectionDAO;
import org.ajabshahar.platform.models.Reflection;

public class Reflections {

    private final ReflectionDAO reflectionRepository;

    public Reflections(ReflectionDAO reflectionRepository) {
        this.reflectionRepository = reflectionRepository;
    }

    public Reflection create(Reflection reflection) {
        return reflectionRepository.create(reflection);
    }
}
