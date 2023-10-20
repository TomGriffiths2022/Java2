package org.example.annotationsandreflection;

// annotations by themselves don't do anything
// they require a 3rd party (compiler, framework) to read them and then act
// they are like formal comments
@Component
public class Worker {

    private String name;

    public Worker() {}

    public Worker(String name) {
        this();
        this.name = name;
    }

    public void work() {
        System.out.println("Working...");
    }
}
