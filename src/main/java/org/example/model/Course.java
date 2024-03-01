package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Course {

    private String id;
    private String name;
    private List<String> steps;

}
