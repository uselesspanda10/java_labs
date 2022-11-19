package lab2.registration.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для базовой информации о курсе
 */
public class CourseInfo {

    /**
     * идентификатор курса
     */
    private long id;

    /**
     * название курса
     */
    private String name;

    /**
     * краткое описание курса
     */
    private String description;

    /**
     * Список идентификаторов курсов, которые нужно обязательно пройти до начала данного курса
     */
    private List<Long> prerequisites;

    /**
     * список категорий студентов, которые могут посещать курс
     */
    private List<StudentCategory> studentCategories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Long> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<StudentCategory> getStudentCategories() {
        return studentCategories;
    }

    public void setStudentCategories(List<StudentCategory> studentCategories) {
        this.studentCategories = studentCategories;
    }
}
