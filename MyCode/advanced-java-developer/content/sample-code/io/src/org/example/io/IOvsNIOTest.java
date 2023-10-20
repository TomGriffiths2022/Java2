package org.example.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class IOvsNIOTest {

    @Test
    public void testSmallFileReadWithNio() throws IOException {
        var lines = Files.readAllLines(Path.of("./shakespeare.txt"));
        lines.forEach(line -> System.out.println(line));
    }

    @Test
    public void testSmallFileWriteWithNio() throws IOException {
        var names = List.of("Adam", "Arthur", "David", "Kadmin", "Lewis", "Nathan", "Tomos");
        Files.write(Path.of("./names.txt"), names, StandardOpenOption.WRITE);
    }

    @Test
    public void testLargeFileReadWithIO() throws IOException {
        // an external resource opened inside a try-with-resources will be closed automatically
        try (var reader = new BufferedReader(new FileReader("/Users/trainer/Desktop/202304.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                var parts = line.split(",");
                var price = Double.parseDouble(parts[1].replace("\"", ""));
                if (price >= 1_000_000) {
                    System.out.println(line);
                }
                line = reader.readLine();
            }
        }
    }

    @Test
    public void testLargeFileReadWithNio() throws IOException {
        try (var reader = Files.newBufferedReader(Path.of("/Users/trainer/Desktop/202304.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                var parts = line.split(",");
                var price = Double.parseDouble(parts[1].replace("\"", ""));
                if (price >= 10_000_000) {
                    System.out.println(line);
                }
                line = reader.readLine();
            }
        }
    }
}
