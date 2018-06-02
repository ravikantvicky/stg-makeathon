package com.stg.makeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.entities.PrefTopics;

@Repository
public interface PrefTopicsRepository extends JpaRepository<PrefTopics, Integer> {

}
