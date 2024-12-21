package com.shri.controller;

import com.shri.model.Comment;
import com.shri.model.User;
import com.shri.request.CreateCommentRequest;
import com.shri.response.MessageResponse;
import com.shri.service.CommentService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private  UserService userService;


    @PostMapping()
    public ResponseEntity<Comment>createComment(
            @RequestBody CreateCommentRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        Comment createdComment=commentService.createComment(request.getIssueId(),user.getId(),request.getContent());
        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public  ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
        @RequestHeader("Authorization") String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());

        MessageResponse res=new MessageResponse();
        res.setMessage("Comment deleted successfully");

        return  new ResponseEntity<>(res,HttpStatus.OK);
    }


    @GetMapping("/{issueId}")
    public  ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId)
    {
        List<Comment> comments=commentService.findCommentByIssueId(issueId);
        return  new ResponseEntity<>(comments,HttpStatus.OK);
    }



}
