package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.EventBook;

public interface BookMyEventRepository extends JpaRepository<EventBook, Serializable>{

}
