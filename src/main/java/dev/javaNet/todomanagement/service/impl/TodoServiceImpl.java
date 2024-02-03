package dev.javaNet.todomanagement.service.impl;

import dev.javaNet.todomanagement.dto.TodoDto;
import dev.javaNet.todomanagement.entity.Todo;
import dev.javaNet.todomanagement.exception.ResourceNotFoundException;
import dev.javaNet.todomanagement.repository.TodoRepository;
import dev.javaNet.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
         todoRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
         todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo= todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = todoRepository.save(todo);
        return modelMapper.map(updateTodo, TodoDto.class);
    }
}


























