package dev.javaNet.todomanagement.controller;

import dev.javaNet.todomanagement.dto.TodoDto;
import dev.javaNet.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this will allow to define REST APIs
@RequestMapping("api/todos") // to define the base url for all the REST APIs within this TodoController class
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add Todo REST API
    @RequestMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
       TodoDto savedTodo = todoService.addTodo(todoDto);
       return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo REST API, first create the getTodo method and convert it into REST API using spring annotation
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build get All Todos REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

        // another response method
//        return ResponseEntity.ok(todos);
    }

    // Build update Todo REST API
    @PutMapping("{id}") // this "id" is called UI template variable, and @RequestBoby will extract the JSON from the Https request, and converts that JSON into TodoDto java object
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
       TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
       return ResponseEntity.ok(updatedTodo);
    }

    // Build delete Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    // Build complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto updateTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updateTodo);
    }

    // Build incomplete Todo REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto updateTodo = todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(updateTodo);
    }
}

