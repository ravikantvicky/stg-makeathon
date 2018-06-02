package com.stg.makeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.entities.UserSearch;

@Repository
public interface UserSearchRepository extends JpaRepository<UserSearch, Integer> {

}
