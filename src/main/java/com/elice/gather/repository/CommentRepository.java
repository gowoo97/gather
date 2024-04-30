package com.elice.gather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elice.gather.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
