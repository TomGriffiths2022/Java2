package org.example.annotationsandreflection;

public class Main {

    public static void main(String[] args) throws Exception {
        var container = new Container();
        container.scanForAndBuildComponents();
        var worker = (Worker) container.getObjects().get(Worker.class);
        worker.work();
        var greeter = (Greeter) container.getObjects().get(Greeter.class);
        greeter.greet();
    }
}
