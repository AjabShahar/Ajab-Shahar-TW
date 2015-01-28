package org.ajabshahar.api;

import com.google.gson.Gson;
import org.ajabshahar.platform.models.Reflection;

public class ReflectionRepresentationFactory {
    public Reflection create(String jsonWord)
    {
        return new Gson().fromJson(jsonWord,Reflection.class);
    }
}
