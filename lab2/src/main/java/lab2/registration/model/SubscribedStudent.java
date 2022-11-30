package lab2.registration.model;

import lab2.registration.reader.CourseDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubscribedStudent extends Student{

    /**
     * список курсов, на которые подписан студент
     */
    private List<ActionWithStudentOnCourse> studentCourses;

    private StudentCategory studentCategory;

    public SubscribedStudent() throws IOException {
        studentCourses = new ArrayList<ActionWithStudentOnCourse>(new CourseDataReader().readCourseInstance().size());
    }

    public StudentCategory getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public List<ActionWithStudentOnCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<ActionWithStudentOnCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public void subscribeStudentToCourse(ActionWithStudentOnCourse courseInstance){
        this.studentCourses.add(courseInstance);
    }

    public void deleteStudentByCourseInstanceId(long courseInstanceId){
        studentCourses.removeIf(course -> course.getId() == courseInstanceId);
    }

    public int getNumberOfStudentCourses() {
        return (int) studentCourses.stream().
                filter(course -> course != null).
                count();
    }
}