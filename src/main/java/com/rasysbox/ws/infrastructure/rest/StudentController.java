package com.rasysbox.ws.infrastructure.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasysbox.ws.application.dto.StudentEntityDto;
import com.rasysbox.ws.infrastructure.persistence.entity.StudentEntity;
import com.rasysbox.ws.infrastructure.persistence.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentController(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public List<StudentEntityDto> getAllStudents() throws IOException {
        List<StudentEntity> studentEntityList = studentRepository.getStudentEntities();

        List<StudentEntityDto> students = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntityList) {
            StudentEntityDto student = new StudentEntityDto();
            student.setId(studentEntity.getId());
            student.setAdmitYear(studentEntity.getAdmitYear());

            // Deserialization of JSON field to Map<String, Object>.
            if (studentEntity.getData() != null) {
                Map<String, Object> dataMap = objectMapper.readValue(
                        studentEntity.getData(),
                        new TypeReference<Map<String, Object>>() {}
                );
                student.setData(dataMap);
            } else {
                student.setData(null);
            }

            students.add(student);
        }
        return students;
    }

    @PostMapping("/add")
    public void saveStudent(int admitYear, String jsonField) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(jsonField);

            StudentEntity testJsonField = new StudentEntity();
            testJsonField.setData(jsonField);

            Timestamp deviceDatetime = new Timestamp(System.currentTimeMillis());

            StudentEntity student = new StudentEntity();
            student.setAdmitYear(admitYear);
            student.setData(jsonField);
            student.setDeviceDatetime(deviceDatetime);
            studentRepository.saveField(admitYear, jsonField, deviceDatetime);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student", e);
        }
    }
}
