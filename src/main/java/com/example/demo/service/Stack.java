package com.example.demo.service;

import com.example.demo.model.Node;

public interface Stack {

    public Long createStack(int maxSize);

    public Node pushElement(Node node);

    public Node popElement(Long stackId);

    public Node peekElement(Long stackId);


}
