package com.hristo.gateway.repository;

import com.hristo.gateway.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
