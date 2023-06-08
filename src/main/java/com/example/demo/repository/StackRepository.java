package com.example.demo.repository;


import com.example.demo.model.NodeStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<NodeStack,Long> {

    @Query("SELECT nodeStack FROM NodeStack nodeStack WHERE nodeStack.stackId = :stackId")
    public NodeStack findNodeStackByStackId(@Param("stackId") Long stackId);

}
