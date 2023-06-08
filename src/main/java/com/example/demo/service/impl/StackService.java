package com.example.demo.service.impl;

import com.example.demo.model.Node;
import com.example.demo.model.NodeStack;
import com.example.demo.model.exceptions.MaximumSizeSmallerOrEqualToZeroException;
import com.example.demo.model.exceptions.StackDoesNotExistException;
import com.example.demo.model.exceptions.StackIsEmptyException;
import com.example.demo.model.exceptions.StackIsFullException;
import com.example.demo.repository.NodeRepository;
import com.example.demo.repository.StackRepository;
import com.example.demo.service.StackInterface;
import org.springframework.stereotype.Service;

@Service
public class StackService implements StackInterface {


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
    nodeStack.setNodes(null);
    this.stackRepository.save(nodeStack);
    return nodeStack.getStackId();
  }

  @Override
  public Node pushElement(Node node) {

    NodeStack nodeStack =
        this.stackRepository
            .findById(node.getStack().getStackId())
            .orElseThrow(() -> new RuntimeException("Stack does not exist"));

    if (nodeStack.getNodes().size() == nodeStack.getMaxSize()) {
      throw new StackIsFullException();
    }
    nodeStack.getNodes().add(node);
    this.nodeRepository.save(node);
    return node;
  }

  @Override
  public Node popElement(Long stackId) {

    NodeStack nodeStack = this.stackRepository.findNodeStackByStackId(stackId);
    if (nodeStack.getNodes().isEmpty()) {
      throw new StackIsEmptyException();
    }
    Node nodeForRemoval = this.getLastNode(stackId);
    nodeStack.getNodes().remove(nodeForRemoval);
     this.nodeRepository.delete(nodeForRemoval);
     return nodeForRemoval;
  }

  @Override
  public Node peekElement(Long stackId) {
    return this.getLastNode(stackId);
  }

  public Node getLastNode(Long stackId){
    NodeStack nodeStack = this.stackRepository.findNodeStackByStackId(stackId);
    if (nodeStack == null) {
      throw new StackDoesNotExistException();
    }
    return nodeStack.getNodes().get(nodeStack.getNodes().size() - 1);
}
}

