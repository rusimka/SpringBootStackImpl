package com.example.demo.service.impl;

import com.example.demo.model.Node;
import com.example.demo.model.NodeStack;
import com.example.demo.model.exceptions.MaximumSizeSmallerOrEqualToZeroException;
import com.example.demo.repository.NodeRepository;
import com.example.demo.repository.StackRepository;
import com.example.demo.service.StackInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StackInterfaceImpl implements StackInterface {

  private Node topNode;

  private final StackRepository stackRepository;
  private final NodeRepository nodeRepository;

  public StackInterfaceImpl(StackRepository stackRepository, NodeRepository nodeRepository) {
    this.stackRepository = stackRepository;
    this.nodeRepository = nodeRepository;
  }

  @Override
  public NodeStack createStack(int maxSize) {
    if (maxSize <= 0) {
      throw new MaximumSizeSmallerOrEqualToZeroException();
    }
    NodeStack nodeStack = new NodeStack(maxSize);
    nodeStack.setNodes(null);
    this.topNode = null;
    this.stackRepository.save(nodeStack);
    return nodeStack;
  }

  @Override
  public Node pushElement(Node node) {

    NodeStack nodeStack =
        this.stackRepository
            .findById(node.getStack().getStackId())
            .orElseThrow(() -> new RuntimeException("Stack does not exist"));

    if (nodeStack.getNodes().size() == nodeStack.getMaxSize()) {
      throw new RuntimeException("Stack is full");
    }
    if (nodeStack.getNodes().isEmpty()) {
      nodeStack.setNodes(new ArrayList<>());
      node.setNext(null);
      node.setIndex(1);
      nodeStack.getNodes().add(node);
      this.nodeRepository.save(node);
    } else {
      Node lastNode = nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
      lastNode.setNext(node);
      node.setIndex(lastNode.getIndex() + 1);
      nodeStack.getNodes().add(node);
      this.nodeRepository.save(node);
    }
    return node;
  }

  @Override
  public NodeStack popElement(Long stackId) {
    NodeStack nodeStack =
        this.stackRepository
            .findById(stackId)
            .orElseThrow(() -> new RuntimeException("Stack does not exist"));
    Node nodeForRemoval = nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
    nodeStack.getNodes().remove(nodeForRemoval);
    Node lastNode = nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
    lastNode.setNext(null);
    this.nodeRepository.delete(nodeForRemoval);

    return nodeStack;
  }

  @Override
  public Node peekElement(Long stackId) {
    NodeStack nodeStack =
        this.stackRepository
            .findById(stackId)
            .orElseThrow(() -> new RuntimeException("Stack does not exist"));
    Node lastNode = nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
    return lastNode;

  }
}
