package com.shri.service;

import com.shri.model.Issue;
import com.shri.model.User;
import com.shri.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {


    Issue getIssueById(Long issueId) throws  Exception;

    List<Issue> getIssueByProjectId (Long projectId) throws  Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    Optional <Issue>updateIssue(Long issueId,IssueRequest updatedIssue,Long userId) throws  Exception;

    void deleteIssue(Long issueId,Long userId) throws  Exception;

    List<Issue> getIssuesByAssigneeId(Long assigneeId)  throws  Exception;


    List<Issue> searchIssue(String title,String status,String priority,Long AssigneeId) throws  Exception;






    Issue addUserToIssue(Long issueId,Long userId) throws  Exception;

    Issue updateStatus(Long issueId,String status) throws  Exception;

}
