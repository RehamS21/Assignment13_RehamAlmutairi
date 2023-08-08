package com.example.assignment13_part1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task_tracker {
    private String id;
    private String title;
    private String description;
    private String status;

}
