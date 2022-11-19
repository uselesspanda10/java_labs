package lab2.registration.model;

import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.StudentDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CourseInstructor extends Instructor{

    private List<ActionWithStudentOnCourse> teachingCourses;

    private int numOfTeachingCourses;

    private List<SubscribedStudent> myStudents;


    public CourseInstructor() throws IOException {
        numOfTeachingCourses = 0;
        this.myStudents = new ArrayList<SubscribedStudent>(new StudentDataReader().readBachelorStudentData().size() + new StudentDataReader().readMasterStudentData().size());
        this.teachingCourses = new ArrayList<ActionWithStudentOnCourse>(new CourseDataReader().readCourseInstanceWithSubscribe().size());
    }

    public void addAllTeachingCourses(List<ActionWithStudentOnCourse> allCourses) {
        allCourses.stream().
                filter(course -> course.getInstructorId() == this.getId()).
                forEach(course -> {
                    teachingCourses.add(course);
                    numOfTeachingCourses++;
                });
    }

    public void addStudent(SubscribedStudent student) {
        AtomicBoolean alreadyHaveStudent = new AtomicBoolean(false);
        myStudents.stream().
                filter(myStudent -> myStudent == student).
                forEach(myStudent -> {
                    alreadyHaveStudent.set(true);
                });
        if(!alreadyHaveStudent.get()) {
            myStudents.add(student);
        }
    }

    public List<SubscribedStudent> getMyStudents() {
        return myStudents;
    }

    public void tryDeleteStudent(SubscribedStudent student) {
        int currentStudentIndex = -1;

        currentStudentIndex = IntStream.range(0, getNumberOfMyStudents())
                .filter(i -> myStudents.get(i).getId() == student.getId()).
                findAny().
                getAsInt();

        int counterOfCourses = 0;

        SubscribedStudent curStudent= myStudents.get(currentStudentIndex);
        if(currentStudentIndex != -1) {
            for (int i = 0; i < numOfTeachingCourses; i++) {
                long teachingCourseId = teachingCourses.get(i).getId();

                counterOfCourses = (int) IntStream.range(0, curStudent.getNumberOfStudentCourses()).
                        filter(j -> curStudent.getStudentCourses().get(j).getId() == teachingCourseId).
                        count();
                if(counterOfCourses >= 1) {
                    break;
                }
            }

            if(counterOfCourses <= 1) {
                deleteStudent(student);
            }
        }
    }

    public void deleteStudent(SubscribedStudent student) {
        myStudents.removeIf(myStudent -> myStudent.getId() == student.getId());
    }

    public int getNumberOfMyStudents() {
        return (int) myStudents.stream().
                filter(student -> student != null).count();
    }
}