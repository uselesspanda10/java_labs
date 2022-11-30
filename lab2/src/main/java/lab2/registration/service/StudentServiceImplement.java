package lab2.registration.service;

import lab2.registration.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class StudentServiceImplement implements StudentService{

    private List<CourseInfo> coursesInfo;

    private List<ActionWithStudentOnCourse> coursesWithStudents;

    private List<SubscribedStudent> courseStudents;

    private List<CourseInstructor> courseInstructors;

    public StudentServiceImplement(List<CourseInfo> coursesInfo, List<ActionWithStudentOnCourse> coursesWithStudents, List<SubscribedStudent> courseStudents, List<CourseInstructor> courseInstructors){
        this.coursesInfo = coursesInfo;
        this.coursesWithStudents = coursesWithStudents;
        this.courseStudents = courseStudents;
        this.courseInstructors = courseInstructors;
    }

    @Override
    public ActionStatus subscribe(long studentId, long courseId) {

        int currentCourseInstanceIndex = -1;
        int currentStudentIndex = -1;
        int currentInstructorIndex = -1;
        try {
            currentStudentIndex = IntStream.range(0, coursesWithStudents.size())
                    .filter(i -> courseStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }

            currentCourseInstanceIndex = IntStream.range(0, coursesWithStudents.size())
                    .filter(i -> coursesWithStudents.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            int curCourseIns = currentCourseInstanceIndex;
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> coursesWithStudents.get(curCourseIns).getInstructorId() == courseInstructors.get(i).getId()).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Не найден инструктор курса!");
            }

            if (!coursesWithStudents.get(currentCourseInstanceIndex).getStartDate().isAfter(LocalDate.now())) {
                throw new Exception("Курс уже начался!");
            }

            int currentCourseIndex = -1;

            currentCourseIndex = IntStream.range(0, coursesInfo.size())
                    .filter(i -> coursesInfo.get(i).getId() == coursesWithStudents.get(curCourseIns).getCourseId()).
                    findAny().
                    getAsInt();

            boolean correctStudentCategory = false;

            int curStud = currentStudentIndex;
            int curCourse = currentCourseIndex;
            correctStudentCategory = IntStream.range(0, coursesInfo.get(currentCourseIndex).getStudentCategories().size()).
                    anyMatch(i -> coursesInfo.get(curCourse).getStudentCategories().get(i) == courseStudents.get(curStud).getStudentCategory());

            if (!correctStudentCategory) {
                throw new Exception("Категория студента не соответствует требованиям курса!");
            }

            if(coursesInfo.get(currentCourseIndex).getPrerequisites() != null) {

                int counterPrerequisites = 0;

                for (int i = 0; i < coursesInfo.get(currentCourseIndex).getPrerequisites().size(); i++) {
                    int curInd = i;
                    int index = IntStream.range(0, courseStudents.get(currentStudentIndex).getCompletedCourses().size())
                            .filter(j -> coursesInfo.get(curCourse).getPrerequisites().get(curInd) == courseStudents.get(curStud).getCompletedCourses().get(j))
                            .findFirst()
                            .orElse(-1);
                    if (index != -1) {
                        counterPrerequisites++;
                    }
                }

                if (counterPrerequisites != coursesInfo.get(currentCourseIndex).getPrerequisites().size()) {
                    throw new Exception("Для записи студентом не пройденны все необходимые курсы!");
                }
            }

            if(coursesWithStudents.get(currentCourseInstanceIndex).getCapacity() != 0) {
                if (coursesWithStudents.get(currentCourseInstanceIndex).getCapacity() == coursesWithStudents.get(currentCourseInstanceIndex).getNumberOfSubscribedStudents()) {
                    throw new Exception("На курсе нет мест!");
                }
            }

            for (int i = 0; i < courseStudents.get(currentStudentIndex).getNumberOfStudentCourses(); i++) {
                if (courseStudents.get(currentStudentIndex).getStudentCourses().get(i).getId() == courseId) {
                    throw new Exception("Студент уже записан на курс!");
                }
            }

        }
        catch (Exception e) {
            return ActionStatus.NOK;
        }
        coursesWithStudents.get(currentCourseInstanceIndex).subscribeStudentOnCourse(courseStudents.get(currentStudentIndex));
        courseStudents.get(currentStudentIndex).subscribeStudentToCourse(coursesWithStudents.get(currentCourseInstanceIndex));
        courseInstructors.get(currentInstructorIndex).addStudent(courseStudents.get(currentStudentIndex));
        return ActionStatus.OK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        int currentCourseInstanceIndex = -1;
        int currentStudentIndex = -1;
        int currentInstructorIndex = -1;

        try {
            currentStudentIndex = IntStream.range(0, courseStudents.size())
                    .filter(i -> courseStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }

            currentCourseInstanceIndex = IntStream.range(0, coursesWithStudents.size())
                    .filter(i -> coursesWithStudents.get(i).getId() == courseId).
                    findAny().
                    getAsInt();

            if (currentCourseInstanceIndex == -1) {
                throw new Exception("Несуществующий идентификатор курса!");
            }

            int curCourseIns = currentCourseInstanceIndex;
            currentInstructorIndex = IntStream.range(0, courseInstructors.size())
                    .filter(i -> coursesWithStudents.get(curCourseIns).getInstructorId() == courseInstructors.get(i).getId()).
                    findAny().
                    getAsInt();

            if (currentInstructorIndex == -1) {
                throw new Exception("Не найден инструктор курса!");
            }

            if (!coursesWithStudents.get(currentCourseInstanceIndex).getStartDate().isAfter(LocalDate.now())) {
                throw new Exception("Курс уже начался!");
            }

        }
        catch (Exception e) {
            return ActionStatus.NOK;
        }

        for(int i = 0; i < coursesWithStudents.get(currentCourseInstanceIndex).getNumberOfSubscribedStudents(); i++) {
            if(coursesWithStudents.get(currentCourseInstanceIndex).getSubscribedStudent().get(i).getId() == studentId) {
                courseInstructors.get(currentInstructorIndex).tryDeleteStudent(courseStudents.get(currentStudentIndex));
                coursesWithStudents.get(currentCourseInstanceIndex).deleteStudentFromCourseById(studentId);
                courseStudents.get(currentStudentIndex).deleteStudentByCourseInstanceId(courseId);
                return ActionStatus.OK;
            }
        }
        return ActionStatus.NOK;
    }

    @Override
    public List<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {

        int currentStudentIndex = -1;

        try {
            currentStudentIndex = IntStream.range(0, courseStudents.size())
                    .filter(i -> courseStudents.get(i).getId() == studentId).
                    findAny().
                    getAsInt();

            if (currentStudentIndex == -1) {
                throw new Exception("Несуществующий идентификатор студента!");
            }
        }
        catch (Exception e) {
            return null;
        }

        return null;
    }
}