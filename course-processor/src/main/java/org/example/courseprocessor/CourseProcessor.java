package org.example.courseprocessor;

public class CourseProcessor {

    public static List<Course> getJavaCourses(List<Course> courses) {
        return courses.stream()
                .filter(course -> List.of("Java 1", "Java 2", "JEE", "Spring").contains(course.getName()))
                .collect(Collectors.toList());
    }


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

    public static List<StartDateDurationAndTrainerName> getTrainerAvailability(
            List<Course> courses, String trainerName) {

        return courses.stream()
                .filter(course -> trainerName.equals(
                        String.format("%s %s", course.getTrainer().getFirstName(), course.getTrainer().getLastName())))
                .map(StartDateDurationAndTrainerName::new)
                .collect(Collectors.toList());
    }

    public static List<String> getDistinctCourseNames(List<Course> courses) {
        return courses.stream()
                .map(Course::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> getDelegatesForCourse(List<Course> courses, String courseName) {
        return courses.stream()
                .filter(course -> course.getName().equals(courseName))
                .flatMap(course -> course.getDelegates().stream())
                .collect(Collectors.toList());
    }

    public static List<Course> getCoursesForDelegate(List<Course> courses, String delegateName) {
        return courses.stream()
                .filter(course -> course.getDelegates().stream()
                        .anyMatch(delegate -> delegateName.equals(
                                String.format("%s %s", delegate.getFirstName(), delegate.getLastName()))))
                .collect(Collectors.toList());
    }

    public static OptionalDouble getAverageNumberOfDelegatesForCourse(
            List<Course> courses, String courseName) {

        return courses.stream()
                .filter(course -> course.getName().equals(courseName))
                .mapToInt(course -> course.getDelegates().size())
                .average();
    }

}
