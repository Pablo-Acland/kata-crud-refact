package co.com.sofka.crud.service;

import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todorepository;

    public Iterable<Todo> list(){
        return todorepository.findAll();
    }

    public Todo save(Todo todo){
        return todorepository.save(todo);
    }

    public void delete(Long id){
        todorepository.delete(getId(id));
    }

    public Todo getId(Long id){
         return todorepository.findById(id).orElseThrow();
    }

    public Iterable<Todo> getIdlist(long Idlist){return  todorepository.findByIdlist(Idlist);}



}
