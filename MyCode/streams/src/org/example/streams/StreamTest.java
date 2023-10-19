package org.example.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamTest {

    // Stream: the movement of data from a to b
    // IO Stream: an object that deals with data moving into/out of your app
    // java.util.Stream is different; it is used to transform a collection of data
    // It is like a production line where operations may be performed on the data mid-flow
    // Each operation results in a new Stream
    // 3 types of operator:
    //  - creational
    //  - intermediate (perform the transformation)
    //  - terminal
    // The purpose of Streams is to simplify transformations; to make the code declarative as opposed to imperative

    @Test
    public void testOldSchoolTransformation() {
        var numbers = List.of(1, 2, 3, 4, 5);
        var oddNumbers = new ArrayList<Integer>();
        for (var number : numbers) {
            if (number % 2 != 0) {
                oddNumbers.add(number);
            }
        }
        assertTrue(numbers.containsAll(List.of(1, 2, 3, 4, 5)));
        assertTrue(oddNumbers.containsAll(List.of(1, 3, 5)));
    }

    @Test
    public void testStreamTransformation() {
        var numbers = List.of(1, 2, 3, 4, 5);
        var oddNumbers = numbers.stream().filter(n -> n % 2 != 0).toList();

        assertTrue(numbers.containsAll(List.of(1, 2, 3, 4, 5)));
        assertTrue(oddNumbers.containsAll(List.of(1, 3, 5)));
    }

    @Test
    public void testCreationalOperators() {
        var s1 = Set.of(1, 2, 3).stream();

        // you can use keySet, values or entrySet to create a Stream from a Map
        var s2 = Map.of("London", "Uk", "Paris", "France", "Berlin", "Germany")
                                    .entrySet().stream();

        var s3 = Stream.of(1, 2, 3);

        var s4 = Stream.iterate(1, n -> n + 2);
    }

    @Test
    public void testIntermediateOperators(){
        var numbers = List.of(1, 5, 3, 2, 4, 3);
        numbers.stream()
                .filter(n -> n % 2 != 0)   // if you need to filter, do it first
                .map(n -> "The number is " + n)
                .distinct() // strips out the duplicates
                .sorted()
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void testFlatMap(){
        var team1 = new Team("Red");
        team1.addPlayers(
                new Player("Dave", "Forward"),
                new Player("Tom", "Midfielder"),
                new Player("Roger", "Defender")
        );
        var team2 = new Team("Blue");
        team2.addPlayers(
                new Player("Fred", "Forward"),
                new Player("Harry", "Midfielder"),
                new Player("Steve", "Defender")
        );
        var teams = List.of(team1, team2);
        var defenders = teams.stream()
                // flatMap is used to deal with one-to-many associations
                // the Function argument must return a Stream
                // the element of the inner Stream will be published on the outer Stream
                .flatMap(team -> team.getPlayers().stream())
                .filter(player -> player.getPosition().equalsIgnoreCase("Defender"))
                .map(Player::getName)
                .toList();
        assertTrue(defenders.containsAll(List.of("Roger", "Steve")));
        assertEquals(2, defenders.size());
    }
}
