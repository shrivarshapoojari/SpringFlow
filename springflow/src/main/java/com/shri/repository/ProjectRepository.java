package com.shri.repository;


import com.shri.model.Project;
import com.shri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {


    List<Project> findByOwner(User user);



      @Query("SELECT p FROM Project p join p.team t where t=:user")
      List<Project>findProjectByTeam(@Param("user")User user);
    List<Project> findByNameContainingAndTeamContaining(String partialName,User user);
         List<Project> findByTeamContainingOrOwner(User user,User owner);



}




