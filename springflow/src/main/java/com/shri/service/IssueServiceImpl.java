package com.shri.service;

import com.shri.model.Issue;
import com.shri.model.Project;
import com.shri.model.User;
import com.shri.repository.IssueRepository;
import com.shri.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private  ProjectService projectService;

    @Autowired
    private  UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue= issueRepository.findById(issueId);
        if(issue.isPresent())
            return issue.get();

        throw  new Exception("No issue found with given id" +issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectID(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
         Project project=projectService.getProjectById(issueRequest.getProjectID());
         Issue issue=new Issue();
         issue.setTitle(issueRequest.getTitle());
         issue.setDescription(issueRequest.getDescription());
         issue.setStatus(issueRequest.getStatus());
         issue.setProjectID(issueRequest.getProjectID());
         issue.setPriority(issueRequest.getPriority());
         issue.setDueDate(issueRequest.getDueDate());

         issue.setProject(project);

         return issueRepository.save(issue);


    }

    @Override
    public Optional<Issue> updateIssue(Long issueId, IssueRequest updatedIssue, Long userId) throws Exception {
        return Optional.empty();
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {

          getIssueById(issueId);
          issueRepository.deleteById(issueId);
    }

    @Override
    public List<Issue> getIssuesByAssigneeId(Long assigneeId) throws Exception {
        // Fetch the user by ID
        User user = userService.findUserById(assigneeId);

        if (user == null) {
            throw new Exception("User does not exist with the given ID: " + assigneeId);
        }

        return user.getAssignedIssues();
    }


    @Override
    public List<Issue> searchIssue(String title, String status, String priority, Long AssigneeId) throws Exception {
        return List.of();
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
         User user=userService.findUserById(userId);
         if(user==null)
             throw new Exception("User doesnt exist with given id"+ userId);

         Issue issue=getIssueById(issueId);
         issue.setAssignee(user);
         return  issueRepository.save(issue);

    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {

        Issue issue=getIssueById(issueId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
