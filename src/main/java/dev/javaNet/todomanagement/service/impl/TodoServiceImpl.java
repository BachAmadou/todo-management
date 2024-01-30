package dev.javaNet.todomanagement.service.impl;

import dev.javaNet.todomanagement.dto.TodoDto;
import dev.javaNet.todomanagement.entity.Todo;
import dev.javaNet.todomanagement.repository.TodoRepository;
import dev.javaNet.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //METHOD 1 - Convert TodoDto into Todo Jpa entity (this is the normal way)
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

        //METHOD 2 - Convert TodoDto into Todo Jpa entity (using the model mapper method)
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Todo Jpa entity (saving the Todo in the database)
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo Jpa entity object into TodoDto object
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }
}


























