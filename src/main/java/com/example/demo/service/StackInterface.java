package com.example.demo.service;

import com.example.demo.model.Node;
import com.example.demo.model.NodeStack;

public interface StackInterface {

    public NodeStack createStack(int maxSize);

    public Node pushElement(Node node);

    public NodeStack popElement(Long stackId);

    public Node peekElement(Long stackId);


}
