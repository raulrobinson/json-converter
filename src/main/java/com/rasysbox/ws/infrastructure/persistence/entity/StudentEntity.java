package com.rasysbox.ws.infrastructure.persistence.entity;

import com.rasysbox.ws.infrastructure.converter.JsonbConverter;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class StudentEntity {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admit_year", length = 4, nullable = false)
    private int admitYear;

    @Convert(converter = JsonbConverter.class)
    @Column(name = "data", columnDefinition = "jsonb", nullable = false)
    private String data;

    @Column(name = "device_datetime", nullable = false)
    private Timestamp deviceDatetime;
}
