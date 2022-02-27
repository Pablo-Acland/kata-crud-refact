package co.com.sofka.crud.factory;

import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.model.Todo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoFactory {

    public TodoDTO toTodoDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(todo.getId());
        todoDTO.setCompleted(todo.isCompleted());
        todoDTO.setName(todo.getName());

        if(todo.getList() != null)
            todoDTO.setIdlist(todo.getList().getId());

        return todoDTO;
    }

    public List<TodoDTO> toTodoList(List<Todo> todo){
        List<TodoDTO> todoDTO = todo.stream().map(this::toTodoDTO).collect(Collectors.toList());

        return todoDTO;
    }

    public Todo toTodoModel(TodoDTO todoDTO){
        Todo todo = new Todo();
        todo.setId(todoDTO.getId());
        todo.setName(todoDTO.getName());
        todo.setCompleted(todoDTO.isCompleted());

        return todo;
    }
}
