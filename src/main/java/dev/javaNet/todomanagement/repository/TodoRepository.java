package dev.javaNet.todomanagement.repository;

import dev.javaNet.todomanagement.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
