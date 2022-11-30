package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.CourseInstructor;
import lab2.registration.model.Instructor;
import lab2.registration.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InstructorDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<CourseInstructor> readCourseInstructorData() throws IOException {
        List<CourseInstructor> res = Arrays.asList(objectMapper.readValue(new File("src/main/resources/instructors.json"), CourseInstructor[].class));
        return res;
    }

}
