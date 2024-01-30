package dev.javaNet.todomanagement.controller;

import dev.javaNet.todomanagement.dto.TodoDto;
import dev.javaNet.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
