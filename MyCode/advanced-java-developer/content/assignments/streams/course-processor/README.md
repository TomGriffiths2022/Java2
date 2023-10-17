<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Streams Assignment

## Course Processor

[<< back](../../../../README.md#streams)

You're going to take an existing course processor app and add to it methods that exploit streams to extract new information from existing training course data. Download [this Maven project](course-processor.zip), unzip it, and open it in IntelliJ. The app comprises two POJO classes (Course and Person), an empty CourseProcessor class to which you will add your methods, and a unit test named CourseProcessorTest that includes some dummy data.

**Use streams in the construction of each of the CourseProcessor methods**

1. Add to the CourseProcessor class a static method named `getJavaCourses`. It should accept a List of Courses and return a List of Java Courses. Note that Java 1, Java 2, JEE, and Spring are all Java courses.<details>
    <summary>Show me</summary>

    ```java
    public static List<Course> getJavaCourses(List<Course> courses) {
        return courses.stream()
            .filter(course -> List.of("Java 1", "Java 2", "JEE", "Spring").contains(course.getName()))
            .collect(Collectors.toList());
    }
    ```
</details>

2. Add to the CourseProcessorTest class a method named `testGetJavaCourses` and annotate it with `@Test`. It should call CourseProcessor::getJavaCourses passing in the dummy List of courses and then assert that the resultant List has four elements only.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetJavaCourses() {
        var javaCourses = getJavaCourses(courses);
        assertEquals(4, javaCourses.size());
    }
    ```
</details>

3. Add to the CourseProcessor class a static method named `getTrainerAvailability`. It should accept a List of Courses and return a List of start dates, durations (numDays), and trainer names. Hint: you could concatenate the start date, duration, and trainer name as a String but it might be preferable to code a new 'view' class comprising the required fields.<details>
    <summary>Show me</summary>

    ```java
    /*
     * The StartDateDurationAndTrainerName class has three fields:
     * - LocalDate startDate
     * - int numDays
     * - String trainerName
     * It's constructor accepts a Course and sets the fields accordingly
     */
    public static List<StartDateDurationAndTrainerName> getTrainerAvailability(List<Course> courses) {
        return courses.stream()
            .map(StartDateDurationAndTrainerName::new)
            .collect(Collectors.toList());
    }
    ```
</details>

4. Add to the CourseProcessorTest class a method named `testGetTrainerAvailability` and annotate it with `@Test`. It should call CourseProcessor::getTrainerAvailability passing in the dummy List of courses and then assert that the resultant List comprises only start dates, durations, and trainer names.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetTrainerAvailability() {
        var trainerAvailability = getTrainerAvailability(courses);
        assertEquals(of(2021, 2, 1), trainerAvailability.get(0).getStartDate());
        assertEquals(5, trainerAvailability.get(0).getNumDays());
        assertEquals("Alan Lavender", trainerAvailability.get(0).getTrainerName());
    }
    ```
</details>

5. In the CourseProcessor class overload the static method named `getTrainerAvailability` such that it accepts a List of Courses and a trainer's (full name). It should return a List of start dates, durations (numDays), and trainer names for the given trainer only.<details>
    <summary>Show me</summary>

    ```java
    public static List<StartDateDurationAndTrainerName> getTrainerAvailability(
        List<Course> courses, String trainerName) {

        return courses.stream()
            .filter(course -> trainerName.equals(
                String.format("%s %s", course.getTrainer().getFirstName(), course.getTrainer().getLastName())))
            .map(StartDateDurationAndTrainerName::new)
            .collect(Collectors.toList());
    }
    ```
</details>

6. Add to the CourseProcessorTest class a method named `testGetTrainerAvailabilityForGivenTrainer` and annotate it with `@Test`. It should call CourseProcessor::getTrainerAvailability passing in the dummy List of courses and a trainer name, and then assert that the resultant List comprises only start dates, durations, and trainer names for the given trainer.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetTrainerAvailabilityForGivenTrainer() {
        var trainerAvailability = getTrainerAvailability(
            courses, "Stuart Bailey");
        assertEquals(3, trainerAvailability.size());
        assertEquals(of(2021, 3, 23), trainerAvailability.get(0).getStartDate());
        assertEquals(4, trainerAvailability.get(0).getNumDays());
        assertEquals("Stuart Bailey", trainerAvailability.get(0).getTrainerName());
    }
    ```
</details>

7. Add to the CourseProcessor class a static method named `getDistinctCourseNames`. It should accept a List of Courses and return a distinct List of course names sorted alphabetically.<details>
    <summary>Show me</summary>

    ```java
    public static List<String> getDistinctCourseNames(List<Course> courses) {
        return courses.stream()
            .map(Course::getName)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
    ```
</details>

8. Add to the CourseProcessorTest class a method named `testGetDistinctCourseNames` and annotate it with `@Test`. It should call CourseProcessor::getDistinctCourseNames passing in the dummy List of courses and then assert that the resultant List has seven elements only, the first of which is `HTML` and the last of which is `Spring`.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetDistinctCourseNames() {
        var courseNames = getDistinctCourseNames(courses);
        assertEquals(7, courseNames.size());
        assertEquals("HTML", courseNames.get(0));
        assertEquals("Spring", courseNames.get(6));
    }
    ```
</details>

9. Add to the CourseProcessor class a static method named `getDelegatesForCourse`. It should accept a List of Courses and a course name, and return a List of the delegates registered to attend said course. Note that if there exists multiple instances of the given course then the resultant List should include the delegates registered for each instance.<details>
    <summary>Show me</summary>

    ```java
    public static List<String> getDelegatesForCourse(List<Course> courses, String courseName) {
        return courses.stream()
            .filter(course -> course.getName().equals(courseName))
            .flatMap(course -> course.getDelegates().stream())
            .collect(Collectors.toList());
    }
    ```
</details>

10. Add to the CourseProcessorTest class a method named `testGetDelegatesForCourse` and annotate it with `@Test`. It should call CourseProcessor::getDelegatesForCourse passing in the dummy List of courses and a course name, say `Java 1`. It should then assert that the resultant List has 20 elements only.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetDelegatesForCourse() {
        var delegates = getDelegatesForCourse(courses, "Java 1");
        assertEquals(20, delegates.size());
    }
    ```
</details>

11. Add to the CourseProcessor class a static method named `getCoursesForDelegate`. It should accept a List of Courses and a delegate name, and return a List of the courses for which the given delegate is registered to attend.<details>
    <summary>Show me</summary>

    ```java
    public static List<Course> getCoursesForDelegate(List<Course> courses, String delegateName) {
        return courses.stream()
                .filter(course -> course.getDelegates().stream()
                        .anyMatch(delegate -> delegateName.equals(
                                String.format("%s %s", delegate.getFirstName(), delegate.getLastName()))))
                .collect(Collectors.toList());
    }
    ```
</details>

12. Add to the CourseProcessorTest class a method named `testGetCoursesForDelegate` and annotate it with `@Test`. It should call CourseProcessor::getCoursesForDelegate passing in the dummy List of courses and a delegate name, say `Kelvin Begley`. It should then assert that the resultant List has 1 element only and that the course name is `React`.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetCoursesForDelegate() {
        var kelvinsCourses = getCoursesForDelegate(courses, "Kelvin Begley");
        assertEquals(1, kelvinsCourses.size());
        assertEquals("React", kelvinsCourses.get(0).getName());
    }
    ```
</details>

13. Add to the CourseProcessor class a static method named `getAverageNumberOfDelegatesForCourse`. It should accept a List of Courses and a course name, and return the average number of delegates registered to attend said course.<details>
    <summary>Show me</summary>

    ```java
    public static OptionalDouble getAverageNumberOfDelegatesForCourse(
        List<Course> courses, String courseName) {

        return courses.stream()
                .filter(course -> course.getName().equals(courseName))
                .mapToInt(course -> course.getDelegates().size())
                .average();
    }
    ```
</details>

14. Add to the CourseProcessorTest class a method named `testGetAverageNumberOfDelegatesForCourse` and annotate it with `@Test`. It should call CourseProcessor::getAverageNumberOfDelegatesForCourse passing in the dummy List of courses and a course name, say `Python 1`. It should then assert that the return value is 10.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testGetAverageNumberOfDelegatesForCourse() {
        var averageDelegates = getAverageNumberOfDelegatesForCourse(courses, "Python 1");
        assertEquals(10, averageDelegates.getAsDouble());
    }
    ```
</details>

[<< back](../../../../README.md#streams)