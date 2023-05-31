package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "node")
public class Node {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long nodeId;

  @Column(name = "node_data")
  private Integer data;

  @ManyToOne
  @JoinColumn(name = "next_node_id")
  private Node next;

  @Column(name = "node_index")
  private int index = -1;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  private NodeStack stack;

  public Node(Long nodeId, Integer data, Node next, int index, NodeStack stack) {
    this.nodeId = nodeId;
    this.data = data;
    this.next = next;
    this.index = index;
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

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public NodeStack getStack() {
    return stack;
  }

  public void setStack(NodeStack stack) {
    this.stack = stack;
  }
}
