package co.com.sofka.crud.service;

import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.factory.Factory;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todorepository;
    @Autowired
    private Factory todosfactory;


    public List<TodoDTO> list(){
        List<Todo> todo = (List<Todo>) todorepository.findAll();

        return todosfactory.toTodoList(todo);
    }
    public Todo saveTodo(Todo todo){
        return todorepository.save(todo);
    }

    public void delete(Long id){
        todorepository.delete(getId(id));
    }

    public Todo getId(Long id){
         return todorepository.findById(id).orElseThrow();
    }




}
