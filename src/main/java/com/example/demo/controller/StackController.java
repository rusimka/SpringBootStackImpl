package com.example.demo.controller;

import com.example.demo.model.Node;
import com.example.demo.model.NodeStack;
import com.example.demo.service.StackInterface;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/stacks")
public class StackController {

  private final StackInterface stackInterface;

  public StackController(StackInterface stackInterface) {
    this.stackInterface = stackInterface;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NodeStack> createStack(@RequestBody NodeStack nodeStack) {
    int maxSize = nodeStack.getMaxSize();
    nodeStack = stackInterface.createStack(maxSize);
    return ResponseEntity.status(HttpStatus.CREATED).body(nodeStack);
  }

  @PostMapping(value = "/push", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Node> pushElement(@RequestBody Node node) {
    this.stackInterface.pushElement(node);
    return ResponseEntity.status(HttpStatus.CREATED).body(node);
  }

  @DeleteMapping(value = "/pop", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NodeStack> popElement(@RequestBody NodeStack nodeStack) {
    Long stackId = nodeStack.getStackId();
    this.stackInterface.popElement(stackId);
    return ResponseEntity.status(HttpStatus.CREATED).body(nodeStack);
  }

  @GetMapping(value = "/peek/{stackId}")
  public Node peekElement(@PathVariable Long stackId){
    Node node = this.stackInterface.peekElement(stackId);
    return node;
}
}

