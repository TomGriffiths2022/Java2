package org.example.annotationsandreflection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {

    private Map<Class, Object> objects = new HashMap<>();

    public void scanForAndBuildComponents() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // TODO write code to scan for all classes in the app
        // every class is an object; ClassName.class is a ref to the class object
        var classes = List.of(Worker.class, Greeter.class);
        for (var cls : classes) {
            var annotations = cls.getAnnotations();
            for (var annotation : annotations) {
                if (annotation.annotationType() == Component.class || annotation.annotationType() == Service.class) {
                    var object = cls.getDeclaredConstructor().newInstance();
                    objects.put(cls, object);
                }
            }
        }
    }

    public Map<Class, Object> getObjects() {
        return objects;
    }
}
