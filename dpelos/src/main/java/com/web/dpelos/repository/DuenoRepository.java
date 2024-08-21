package com.web.dpelos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Dueno;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {

}