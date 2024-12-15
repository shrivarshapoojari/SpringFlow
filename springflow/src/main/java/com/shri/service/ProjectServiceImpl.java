package com.shri.service;

import com.shri.model.Chat;
import com.shri.model.Project;
import com.shri.model.User;
import com.shri.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements  ProjectService{
    @Autowired
    private ProjectRepository projectrepository;

    @Override
    public Project createProject(Project project, User user) throws Exception {



        return null;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        return List.of();
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        return null;
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {

    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        return null;
    }

    @Override
    public void addUserToProject(Long projectid, Long userId) throws Exception {

    }

    @Override
    public void removeUserFromProject(Long projectid, Long userId) throws Exception {

    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        return null;
    }
}
