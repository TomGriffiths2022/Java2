package org.example.generics;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // the String inside the diamond is a type argument
        var names = new ArrayList<String>();

        // in the old days you could store anything in a List
        var things = new ArrayList();
        things.add("Hello");
        things.add(42); // <!-- The primitive int is boxed in an object of type Integer.
        things.add(false); // <!-- The primitive boolean is boxed in an object of type Boolean.
        var firstThing = things.get(0); // <!-- The return type is Object. All classes ultimately inherit from Object.

        // it's important to note that the variable has a type which is distinct from the object type
        // the variable o is of type Object
        // the variable o references an object of type String
        // it works because String inherits from Object (thank you polymorphism)
        Object o = "Hello";
        String s = (String) o; // <!-- a downcast (we should avoid doing this)

        // the polymorphism rule:
        // - the variable type dictates WHAT you can do
        // - the object type dictates HOW you do it
    }
}
