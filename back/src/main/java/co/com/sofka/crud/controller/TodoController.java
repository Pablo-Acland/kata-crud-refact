package co.com.sofka.crud.controller;

import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.repository.ListRepository;
import co.com.sofka.crud.service.TodoService;
import co.com.sofka.crud.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoservice;
    @Autowired
    private ListRepository listrepository;

    @GetMapping(value = "/all")
    public List<TodoDTO> Todos(){return todoservice.list();}

    @GetMapping(value = "/alltolist/{id}")
    public Iterable<Todo> list(@PathVariable("id") Long list){
        ListModel listodo = listrepository.findById(list).orElseThrow();
       return todoservice.getIdlist(listodo);
    }
    
    @PostMapping(value = "/save/{id}")
    public Todo save(@RequestBody Todo todo){
        return todoservice.saveTodo(todo);
    }

    @PutMapping(value = "/update")
    public Todo update(@RequestBody Todo todo){
        if(todo.getId() != null){
            return ;
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id")Long id){
        todoservice.delete(id);
    }

    @GetMapping(value = "/get/{id}")
    public Todo get(@PathVariable("id") Long id){
        return todoservice.getId(id);
    }

}
