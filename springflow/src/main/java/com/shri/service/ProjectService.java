package com.shri.service;


import com.shri.model.Chat;
import com.shri.model.Project;
import com.shri.model.User;
import com.shri.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project createProject (Project project,User user) throws  Exception;
    List<Project> searchProject(String keyWord,User user) throws  Exception;

    List<Project> getProjectByTeam(User user,String category ,String tag) throws Exception;

     Project getProjectById( Long projectId) throws  Exception;

     void deleteProject(Long projectId,Long userId) throws Exception;


     Project updateProject(Project updatedProject,Long id) throws  Exception;


     void addUserToProject(Long projectid,Long userId)  throws Exception;

    void removeUserFromProject(Long projectid,Long userId)  throws Exception;

    Chat getChatByProjectId(Long projectId) throws  Exception;


}
