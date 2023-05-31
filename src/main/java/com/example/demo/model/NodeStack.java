package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stack")
public class NodeStack {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stackId;

  @OneToMany(mappedBy = "stack")
  private List<Node> nodes;

  @Column(name = "max_size")
  private int maxSize;

  public NodeStack(int maxSize) {
    this.maxSize = maxSize;
  }

  public NodeStack() {

  }


  public Long getStackId() {
    return stackId;
  }

  public void setStackId(Long stackId) {
    this.stackId = stackId;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }
}
