package org.example.courseprocessor.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Course {

    private long id;
    private String name;
    private int numDays;
    private LocalDate startDate;
    private Person trainer;
    private Set<Person> delegates;

    private static long nextId = 1;

    public Course(String name, int numDays, LocalDate startDate, Person trainer) {
        this.id = nextId++;
        this.name = name;
        this.numDays = numDays;
        this.startDate = startDate;
        this.trainer = trainer;
        this.delegates = new HashSet<>();
    }

    public Course withDelegates(Person... delegates) {
        for (Person delegate : delegates) {
            this.delegates.add(delegate);
        }
        return this;
    }

    public Course withDelegates(BufferedReader reader, int numLines) {
        IntStream.range(0, numLines).forEach(n -> {
            try {
                String row = reader.readLine();
                String[] cols = row.split(",");
                this.delegates.add(new Person(cols[0], cols[1], cols[2], cols[3]));
            } catch (IOException e) {
                // ignore
            }
        });
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numDays=" + numDays +
                ", startDate=" + startDate +
                ", trainer=" + trainer +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumDays() {
        return numDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Person getTrainer() {
        return trainer;
    }

    public Set<Person> getDelegates() {
        return Collections.unmodifiableSet(delegates);
    }
}
