package com.stg.makeathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.entities.UserPrefId;
import com.stg.makeathon.entities.UserPrefrences;

@Repository
public interface UserPrefRepository extends JpaRepository<UserPrefrences, UserPrefId> {
	public List<UserPrefrences> findByIdUserIdOrderByClickCountDesc(Integer userId);
}
