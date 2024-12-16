package com.shri.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRequest {
    private  String title;
    private String description;
    private String status;
    private  Long projectID;
    private String priority;
    private LocalDate dueDate;

}
