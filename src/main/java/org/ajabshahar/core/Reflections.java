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
        return reflectionRepository.findBy(landingPage, authoringComplete);
    }


    public Reflection findReflection(int id) {
        return reflectionRepository.find(id).get(0);
    }

    public Reflection update(Reflection updatableReflection) {
        Reflection originalReflection = findReflection((int) updatableReflection.getId());

        return reflectionRepository.update(invokeSetters(originalReflection, updatableReflection));
    }

    private Reflection invokeSetters(Reflection originalReflection, Reflection reflection) {
        originalReflection.setTitle(reflection.getTitle());
        originalReflection.setVerb(reflection.getVerb());
        originalReflection.setSpeaker(reflection.getSpeaker());
        originalReflection.setSoundCloudId(reflection.getSoundCloudId());
        originalReflection.setYoutubeVideo(reflection.getYoutubeVideo());
        originalReflection.setReflectionTranscripts(reflection.getReflectionTranscripts());
        originalReflection.setIsAuthoringComplete(reflection.getIsAuthoringComplete());
        originalReflection.setShowOnFeaturedContentPage(reflection.getShowOnFeaturedContentPage());
        return originalReflection;
    }

    public List<Reflection> findBy(int startFrom, String filteredLetter) {
        return reflectionRepository.findBy(startFrom, filteredLetter);
    }
}
