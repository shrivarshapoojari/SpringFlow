package com.shri.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.mapping.Array;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    private  String fullName;
    private String email;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Issue> assignedIssues=new ArrayList<>();

    private  int projectSize;
}
