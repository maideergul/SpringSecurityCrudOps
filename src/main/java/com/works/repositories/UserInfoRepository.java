package com.works.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {	
	
	long countByName(String name);
}