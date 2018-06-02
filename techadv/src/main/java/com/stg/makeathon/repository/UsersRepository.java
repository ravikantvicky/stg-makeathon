package com.stg.makeathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stg.makeathon.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
