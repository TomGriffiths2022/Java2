package org.example.annotationsandreflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // <!-- @Component can only be applied to classes
@Retention(RetentionPolicy.RUNTIME) // <!-- @Component will exist on the class thru runtime
public @interface Component {
}
