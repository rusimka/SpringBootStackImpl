package com.example.demo.repository;


import com.example.demo.model.NodeStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<NodeStack,Long> {}
