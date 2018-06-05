package com.stg.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.entities.PrefTopics;

@Repository
public interface PrefTopicsRepository extends JpaRepository<PrefTopics, Integer> {
	public PrefTopics findByPrefTopicIgnoreCase(String prefTopic);

	@Query("select p.prefTopic from PrefTopics p where p.prefId in(?1)")
	public List<String> findPrefTopicByPrefIdIn(List<Integer> prefIds);
}
