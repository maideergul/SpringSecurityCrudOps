package com.works.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.entities.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}