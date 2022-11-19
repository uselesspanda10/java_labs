package lab2.registration.service;

import lab2.registration.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class CourseInstructorServiceImplement implements CourseInstructorService {

    private List<CourseInfo> coursesInfo;

    private List<ActionWithStudentOnCourse> coursesWithStudents;

    private List<SubscribedStudent> subscribedStudents;

    private List<CourseInstructor> courseInstructors;

    public CourseInstructorServiceImplement(List<CourseInfo> coursesInfo, List<ActionWithStudentOnCourse> coursesWithStudents, List<SubscribedStudent> subscribedStudents, List<CourseInstructor> courseInstructors){
        this.coursesInfo = coursesInfo;
        this.coursesWithStudents = coursesWithStudents;
        this.subscribedStudents = subscribedStudents;
        this.courseInstructors = courseInstructors;
    }

    @Override
    public List<SubscribedStudent> findStudentsByCourseId(long courseId){
        int currentCourseInstanceIndex = -1;

        try {
            currentCourseInstanceIndex = IntStream.range(0, coursesWithStudents.size())
                    .filter(i -> coursesWithStudents.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }
        }
        catch (Exception e) {
            return null;
        }
        return coursesWithStudents.get(currentCourseInstanceIndex).getSubscribedStudent();
    }

    @Override
    public List<SubscribedStudent> findStudentsByInstructorId(long instructorId) {
        int currentInstructorIndex = -1;
        try {
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> courseInstructors.get(i).getId() == instructorId).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Несуществующий идентификатор преподавателя!");
            }

        }
        catch (Exception e) {
            return null;
        }
        return courseInstructors.get(currentInstructorIndex).getMyStudents();
    }

    @Override
    public List<CourseInstructor> findReplacement(long instructorId, long courseId) {
        int currentInstructorIndex = -1;
        int currentCourseIndex = -1;
        try {
            currentCourseIndex = IntStream.range(0, coursesInfo.size())
                    .filter(i -> coursesInfo.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if(currentCourseIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> courseInstructors.get(i).getId() == instructorId).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Несуществующий идентификатор преподавателя!");
            }

        }
        catch (Exception e) {
            return null;
        }

        List<CourseInstructor> canReplace = new ArrayList<CourseInstructor>(courseInstructors.size() - 1);
        int counterCanReplace = 0;

        for (int i = 0; i < courseInstructors.size(); i++) {
            CourseInstructor courseCanTeach = courseInstructors.get(i);
            if (i != currentInstructorIndex) {

                int index = IntStream.range(0, courseCanTeach.getCanTeach().size())
                        .filter(j -> courseId == courseCanTeach.getCanTeach().get(j))
                        .findFirst()
                        .orElse(-1);

                if (index != -1) {
                    canReplace.add(courseInstructors.get(i));
                    counterCanReplace++;
                }
            }
        }

        List<CourseInstructor> result = new ArrayList<CourseInstructor>(counterCanReplace);

        IntStream.range(0, counterCanReplace)
                .forEach(i -> result.add(canReplace.get(i)));

        return result;
    }
}