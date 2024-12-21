package com.shri.service;

import com.shri.model.Comment;
import com.shri.model.Issue;
import com.shri.model.User;
import com.shri.repository.CommentRepository;
import com.shri.repository.IssueRepository;
import com.shri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements  CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue>issue=issueRepository.findById(issueId);
        Optional<User> user=userRepository.findById(userId);
        if(issue.isEmpty())
            throw  new Exception("Issue not found  with given id"+issueId);

        if(user.isEmpty())
            throw  new Exception("Not authorised to comment");


        Issue getIssue=issue.get();
        User getUser=user.get();

        Comment comment=new Comment();

        comment.setIssue(getIssue);
        comment.setUser(getUser);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment=commentRepository.save(comment);

        getIssue.getComments().add(savedComment);
        return  savedComment;


    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {

        Optional<Comment> commentOptional=commentRepository.findById(commentId);
        Optional<User> userOptional=userRepository.findById(userId);

        if(commentOptional.isEmpty())
            throw  new Exception("Comment not found with given id "+ commentId);

        if(userOptional.isEmpty())
            throw new Exception("No comments found for you "+userId);

        Comment comment= commentOptional.get();
        User user=userOptional.get();
        if(comment.getUser().equals(user))
        {
            commentRepository.delete(comment);
        }
        else {
            throw  new Exception("You are not authorised to delete this comment");

        }

    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return commentRepository.findCommentByIssueId(issueId);
    }


}
