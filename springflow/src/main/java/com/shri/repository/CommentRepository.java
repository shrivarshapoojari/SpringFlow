package com.shri.repository;

import com.shri.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {


    List<Comment> findCommentByIssueId(Long issueId);
}
