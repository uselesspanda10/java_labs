package lab2.registration.model;

import lab2.registration.reader.StudentDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionWithStudentOnCourse extends CourseInstance {

    private List<SubscribedStudent> subscribedStudent;

    public ActionWithStudentOnCourse() throws IOException {
        if (this.getCapacity() == 0) {

            this.subscribedStudent = new ArrayList<SubscribedStudent>(new StudentDataReader().readBachelorStudentData().size() + new StudentDataReader().readMasterStudentData().size());
        }
        else {
            this.subscribedStudent = new ArrayList<SubscribedStudent>(this.getCapacity());
        }
    }

    public List<SubscribedStudent> getSubscribedStudent() {
        return subscribedStudent;
    }

    public void setSubscribedStudent(List<SubscribedStudent> subscribedStudent) {
        this.subscribedStudent = subscribedStudent;
    }

    public int getNumberOfSubscribedStudents() {
        return (int) subscribedStudent.stream().
                filter(student -> student != null).count();
    }

    public void subscribeStudentOnCourse(SubscribedStudent student){
        this.subscribedStudent.add(student);
    }

    public void deleteStudentFromCourseById(long id) {
        subscribedStudent.removeIf(student -> student.getId() == id);
    }
}
