package com.shri.controller;

import com.shri.model.Issue;
import com.shri.model.User;
import com.shri.request.IssueRequest;
import com.shri.response.MessageResponse;
import com.shri.service.IssueService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(
            @PathVariable Long issueId
    )  throws  Exception
    {
        Issue issue =issueService.getIssueById(issueId);
        return new ResponseEntity<>(issue, HttpStatus.OK);

    }

    @GetMapping("/project/{projectid}")
    public ResponseEntity<List<Issue>> getIssuesByProjectId(
            @PathVariable Long projectId
    ) throws  Exception
    {
        List<Issue>issues=issueService.getIssueByProjectId(projectId);
        return  new ResponseEntity<>(issues,HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Issue> createIssue(
            @RequestBody IssueRequest issue,
            @RequestHeader("Authorization") String jwt
            ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);




             Issue createdIssue=issueService.createIssue(issue,user);
            return new ResponseEntity<>(createdIssue,HttpStatus.OK);
    }


    @DeleteMapping("/{issueId}")

    public  ResponseEntity<MessageResponse> deleteIssue(
            @PathVariable Long issueId,
            @RequestHeader String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());
        MessageResponse response=new MessageResponse();
        response.setMessage("issue deleted successfully");
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

}
