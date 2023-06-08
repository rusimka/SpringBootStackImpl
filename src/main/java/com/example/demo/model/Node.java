package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "node")
public class Node {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long nodeId;

  @Column(name = "node_data")
  private Integer data;



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  @JsonBackReference
  private NodeStack stack;

  public Node(Long nodeId, Integer data, NodeStack stack) {
    this.nodeId = nodeId;
    this.data = data;

    this.stack = stack;
  }

  public Node(){}


  public Long getNodeId() {
    return nodeId;
  }

  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }

  public NodeStack getStack() {
    return stack;
  }

  public void setStack(NodeStack stack) {
    this.stack = stack;
  }
}
