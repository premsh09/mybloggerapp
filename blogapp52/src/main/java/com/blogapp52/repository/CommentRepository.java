package com.blogapp52.repository;

import com.blogapp52.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByDraftId(long draftId);
}
