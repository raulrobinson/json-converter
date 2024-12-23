package com.rasysbox.ws.infrastructure.persistence.repository;

import com.rasysbox.ws.infrastructure.persistence.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<StudentEntity> getStudentEntities();

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO student (admit_year, data, device_datetime) VALUES (:admit_year, CAST(:data AS jsonb), :device_datetime)
            """, nativeQuery = true)
    void saveField(@Param("admit_year") int admitYear,
                   @Param("data") String data,
                   @Param("device_datetime") Timestamp deviceDatetime);

}
