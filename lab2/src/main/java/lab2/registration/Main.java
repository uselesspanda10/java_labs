package lab2.registration;

import lab2.registration.model.CourseInstance;
import lab2.registration.model.SubscribedStudent;
import lab2.registration.model.CourseInstructor;
import lab2.registration.service.RunningUpForService;
import lab2.registration.service.StudentServiceImplement;
import lab2.registration.service.CourseInstructorServiceImplement;

import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {
        RunningUpForService data = new RunningUpForService();
        StudentServiceImplement studentService = new StudentServiceImplement(data.getCoursesInfo(),
                data.getCoursesWithStudents(),
                data.getSubscribedStudents(),
                data.getCourseInstructor());
        CourseInstructorServiceImplement courseInstructorService = new CourseInstructorServiceImplement(data.getCoursesInfo(),
                data.getCoursesWithStudents(),
                data.getSubscribedStudents(),
                data.getCourseInstructor());
        studentService.subscribe(103, 100002);
        studentService.subscribe(105, 100002);
        studentService.subscribe(104, 100002);
        studentService.unsubscribe(105, 100002);
        List<SubscribedStudent> instructorStudents = courseInstructorService.findStudentsByInstructorId(9002);
        List<CourseInstance> allSubs104Student = studentService.findAllSubscriptionsByStudentId(104);
        List<SubscribedStudent> studentsOn100002Course = courseInstructorService.findStudentsByCourseId(100002);
        List<CourseInstructor> replaceFor9002 = courseInstructorService.findReplacement(9002, 20935);
    }
}