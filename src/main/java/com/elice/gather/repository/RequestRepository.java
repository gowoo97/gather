package com.elice.gather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elice.gather.entity.Request;

public interface RequestRepository extends JpaRepository<Request,Long>{

}
