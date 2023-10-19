package org.example.courseprocessor;

import org.example.courseprocessor.models.Course;
import org.example.courseprocessor.models.Person;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.time.LocalDate.of;

public class CourseProcessorTest {

    private List<Course> courses;

    @BeforeEach
    public void setUp() throws IOException {
        Person al = new Person("Alan", "Lavender", "alanl@stayahead.com", "207-600-6116");
        Person bs = new Person("Brian", "Short", "brians@stayahead.com", "207-600-6116");
        Person kf = new Person("Kevin", "Fitzsimons", "kevinf@stayahead.com", "207-600-6116");
        Person mb = new Person("Musie", "Beyene", "musieb@stayahead.com", "207-600-6116");
        Person sb = new Person("Stuart", "Bailey", "stuartb@stayahead.com", "207-600-6116");
        BufferedReader reader = new BufferedReader(new FileReader("delegates.csv"));
        courses = List.of(
                new Course("Java 1", 5, of(2021, 2, 1), al).withDelegates(reader, 10),
                new Course("JEE", 5, of(2021, 2, 15), bs).withDelegates(reader, 3),
                new Course("Python 1", 4, of(2021, 2, 16), kf).withDelegates(reader, 8),
                new Course("Python 2", 3, of(2021, 3, 10), mb).withDelegates(reader, 4),
                new Course("Spring", 4, of(2021, 3, 23), sb).withDelegates(reader, 6),
                new Course("HTML", 3, of(2021, 4, 5), al).withDelegates(reader, 1),
                new Course("Python 1", 4, of(2021, 4, 13), kf).withDelegates(reader, 12),
                new Course("React", 4, of(2021, 4, 27), sb).withDelegates(reader, 3),
                new Course("React", 4, of(2021, 5, 11), sb).withDelegates(reader, 3),
                new Course("Java 1", 5, of(2021, 2, 7), al).withDelegates(reader, 10)
        );
        reader.close();
    }

    @Test
    public void testGetJavaCourses() {
        var javaCourses = getJavaCourses(courses);
        assertEquals(4, javaCourses.size());
    }

    @Test
    public void testGetTrainerAvailability() {
        var trainerAvailability = getTrainerAvailability(courses);
        assertEquals(of(2021, 2, 1), trainerAvailability.get(0).getStartDate());
        assertEquals(5, trainerAvailability.get(0).getNumDays());
        assertEquals("Alan Lavender", trainerAvailability.get(0).getTrainerName());
    }

    @Test
    public void testGetTrainerAvailabilityForGivenTrainer() {
        var trainerAvailability = getTrainerAvailability(
                courses, "Stuart Bailey");
        assertEquals(3, trainerAvailability.size());
        assertEquals(of(2021, 3, 23), trainerAvailability.get(0).getStartDate());
        assertEquals(4, trainerAvailability.get(0).getNumDays());
        assertEquals("Stuart Bailey", trainerAvailability.get(0).getTrainerName());
    }

    @Test
    public void testGetDistinctCourseNames() {
        var courseNames = getDistinctCourseNames(courses);
        assertEquals(7, courseNames.size());
        assertEquals("HTML", courseNames.get(0));
        assertEquals("Spring", courseNames.get(6));
    }

    @Test
    public void testGetDelegatesForCourse() {
        var delegates = getDelegatesForCourse(courses, "Java 1");
        assertEquals(20, delegates.size());
    }

    @Test
    public void testGetCoursesForDelegate() {
        var kelvinsCourses = getCoursesForDelegate(courses, "Kelvin Begley");
        assertEquals(1, kelvinsCourses.size());
        assertEquals("React", kelvinsCourses.get(0).getName());
    }

    @Test
    public void testGetAverageNumberOfDelegatesForCourse() {
        var averageDelegates = getAverageNumberOfDelegatesForCourse(courses, "Python 1");
        assertEquals(10, averageDelegates.getAsDouble());
    }
}
