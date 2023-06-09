package com.example.demo.controller;

import com.example.demo.model.Node;
import com.example.demo.service.Stack;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stacks")
public class StackController {

  private final Stack stackInterface;

  public StackController(Stack stackInterface) {
    this.stackInterface = stackInterface;
  }

  @PostMapping
  public ResponseEntity<Long> createStack(@RequestParam("maxSize") int maxSize) {
    return ResponseEntity.status(HttpStatus.CREATED).body(stackInterface.createStack(maxSize));
  }

  /*
  This functionality can be modified by sending the 'stackId' as Query Parameter, and 'node_data' as Request Body.

  Also, I am familiar with the concept of handling exceptions on the Controller side, but in the interest of time,I haven't implemented.
   */
  @PostMapping(value = "/push", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Node> pushElement(@RequestBody Node node) {
    this.stackInterface.pushElement(node);
    return ResponseEntity.status(HttpStatus.CREATED).body(node);
  }

  @DeleteMapping(value = "/pop/{stackId}")
  public ResponseEntity<Node> popElement(@PathVariable Long stackId) {
    return ResponseEntity.ok(this.stackInterface.popElement(stackId));
  }

  @GetMapping(value = "/peek/{stackId}")
  public ResponseEntity<Node> peekElement(@PathVariable Long stackId) {
    Node node = this.stackInterface.peekElement(stackId);
    return ResponseEntity.ok(node);
  }
}
