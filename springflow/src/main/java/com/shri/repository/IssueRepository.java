package com.shri.repository;

import com.shri.model.Issue;
import com.shri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository  extends JpaRepository<Issue,Long> {
  public List<Issue> findByProjectID(Long id);
 public List<Issue> findByAssignee(User assignee);
}
