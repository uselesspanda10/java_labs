package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.ActionWithStudentOnCourse;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CourseDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseDataReader(){
        objectMapper.findAndRegisterModules();
    }

    public List<CourseInfo> readCourseInfoData() throws IOException {
        List<CourseInfo> res = Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class));
        return res;
    }

    public List<CourseInstance> readCourseInstance() throws IOException {
        List<CourseInstance> res = Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class));
        return res;
    }

    public List<ActionWithStudentOnCourse> readCourseInstanceWithSubscribe() throws IOException {
        List<ActionWithStudentOnCourse> res = Arrays.asList(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), ActionWithStudentOnCourse[].class));
        return res;
    }
}