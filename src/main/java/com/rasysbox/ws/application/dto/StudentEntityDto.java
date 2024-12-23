package com.rasysbox.ws.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StudentEntityDto {
    private Long id;
    private int admitYear;
    private Map<String, Object> data;
    private String deviceDatetime;
}
