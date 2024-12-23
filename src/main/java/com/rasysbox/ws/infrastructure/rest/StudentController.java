package com.rasysbox.ws.infrastructure.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasysbox.ws.application.dto.StudentEntityDto;
import com.rasysbox.ws.infrastructure.persistence.entity.StudentEntity;
import com.rasysbox.ws.infrastructure.persistence.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/students", produces = "application/json")
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
            student.setDeviceDatetime(String.valueOf(studentEntity.getDeviceDatetime()));

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
    public void saveStudent(@RequestParam("admitYear") int admitYear,
                            @RequestParam("jsonField") String jsonField) {
        try {
            Timestamp deviceDatetime = new Timestamp(System.currentTimeMillis());
            studentRepository.saveField(
                    admitYear,
                    jsonField,
                    deviceDatetime);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student", e);
        }
    }
}
