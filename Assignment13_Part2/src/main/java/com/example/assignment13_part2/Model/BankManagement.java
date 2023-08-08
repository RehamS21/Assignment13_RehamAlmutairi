package com.example.assignment13_part2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankManagement {
    private String id;
    private String username;
    private double balance;
}
