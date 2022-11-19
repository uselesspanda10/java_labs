package lab2.registration.service;

import lab2.registration.model.*;
import lab2.registration.reader.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RunningUpForService {

    private List<CourseInfo> coursesInfo;

    private List<ActionWithStudentOnCourse> coursesWithStudents;

    private List<SubscribedStudent> subscribedStudents;

    private List<CourseInstructor> courseInstructors;


    public RunningUpForService() throws IOException {
        courseInstructors = new InstructorDataReader().readCourseInstructorData();
        coursesInfo = new CourseDataReader().readCourseInfoData();
        coursesWithStudents = new CourseDataReader().readCourseInstanceWithSubscribe();

        List<SubscribedStudent> bachelorsArray = new StudentDataReader().readBachelorStudentData();
        List<SubscribedStudent> mastersArray = new StudentDataReader().readMasterStudentData();

        subscribedStudents = new ArrayList<SubscribedStudent>(bachelorsArray.size() + mastersArray.size());

        IntStream.range(0, bachelorsArray.size())
                .forEach(i -> {
                    subscribedStudents.add(bachelorsArray.get(i));
                    subscribedStudents.get(i).setStudentCategory(StudentCategory.BACHELOR);
                });

        IntStream.range(bachelorsArray.size(), mastersArray.size() + bachelorsArray.size())
                .forEach(i -> {
                    subscribedStudents.add(mastersArray.get(i - bachelorsArray.size()));
                    subscribedStudents.get(i).setStudentCategory(StudentCategory.MASTER);
                });

        courseInstructors.stream().
                forEach(course -> course.addAllTeachingCourses(coursesWithStudents));
    }

    public List<CourseInfo> getCoursesInfo() {
        return coursesInfo;
    }

    public void setCoursesInfo(List<CourseInfo> coursesInfo) {
        this.coursesInfo = coursesInfo;
    }

    public List<ActionWithStudentOnCourse> getCoursesWithStudents() {
        return coursesWithStudents;
    }

    public void setCoursesWithStudents(List<ActionWithStudentOnCourse> coursesWithStudents) {
        this.coursesWithStudents = coursesWithStudents;
    }

    public List<SubscribedStudent> getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(List<SubscribedStudent> subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }

    public List<CourseInstructor> getCourseInstructor() {
        return courseInstructors;
    }

    public void setCourseInstructors(List<CourseInstructor> courseInstructors) {
        this.courseInstructors = courseInstructors;
    }
}