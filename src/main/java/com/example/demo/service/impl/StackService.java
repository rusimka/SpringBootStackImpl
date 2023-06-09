package com.example.demo.service.impl;

import com.example.demo.model.Node;
import com.example.demo.model.NodeStack;
import com.example.demo.model.exceptions.MaximumSizeSmallerOrEqualToZeroException;
import com.example.demo.model.exceptions.StackDoesNotExistException;
import com.example.demo.model.exceptions.StackIsEmptyException;
import com.example.demo.model.exceptions.StackIsFullException;
import com.example.demo.repository.NodeRepository;
import com.example.demo.repository.StackRepository;
import com.example.demo.service.Stack;
import org.springframework.stereotype.Service;

@Service
public class StackService implements Stack {

  private final StackRepository stackRepository;
  private final NodeRepository nodeRepository;

  public StackService(StackRepository stackRepository, NodeRepository nodeRepository) {
    this.stackRepository = stackRepository;
    this.nodeRepository = nodeRepository;
  }

  @Override
  public Long createStack(int maxSize) {
    if (maxSize <= 0) {
      throw new MaximumSizeSmallerOrEqualToZeroException();
    }
    NodeStack nodeStack = new NodeStack(maxSize);
    this.stackRepository.save(nodeStack);
    return nodeStack.getStackId();
  }

  @Override
  public Node pushElement(Node node) {

    NodeStack nodeStack = findNodeStackByStackId(node.getStack().getStackId());

    if (isFull(nodeStack)) {
      throw new StackIsFullException();
    }
    nodeStack.getNodes().add(node);
    this.nodeRepository.save(node);
    return node;
  }

  @Override
  public Node popElement(Long stackId) {

    NodeStack nodeStack = findNodeStackByStackId(stackId);

    Node nodeForRemoval = this.getLastNode(nodeStack);
    nodeStack.getNodes().remove(nodeForRemoval);
    this.nodeRepository.delete(nodeForRemoval);
    return nodeForRemoval;
  }

  @Override
  public Node peekElement(Long stackId) {
    NodeStack nodeStack = findNodeStackByStackId(stackId);
    return this.getLastNode(nodeStack);
  }

  public NodeStack findNodeStackByStackId(Long stackId) {
    return this.stackRepository.findById(stackId).orElseThrow(StackDoesNotExistException::new);
  }

  public Node getLastNode(NodeStack nodeStack) {
    if (isEmpty(nodeStack)) {
      throw new StackIsEmptyException();
    }
    return nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
  }

  public boolean isFull(NodeStack nodeStack) {
    return nodeStack.getNodes().size() == nodeStack.getMaxSize();
  }

  public boolean isEmpty(NodeStack nodeStack) {
    return nodeStack.getNodes().isEmpty();
  }
}
