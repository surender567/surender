package com.surender.narlakanti.developer.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surender.narlakanti.developer.library.model.Library;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findByName(String name);
}