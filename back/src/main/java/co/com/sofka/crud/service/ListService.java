package co.com.sofka.crud.service;

import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.ListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ListService")
public class ListService {
    @Autowired
    private ListRepository listrepository;
    @Autowired
    private TodoRepository todorepository;
    @Autowired
    private ListService listservice;


    public Iterable<ListModel> list(){
        return listrepository.findAll();
    }
    public ListModel save(ListModel list){
        return listrepository.save(list);
    }

    public void deleteByIdlist(Long Idlist){
        List<Todo> todos = todorepository.findByIdlist(Idlist);
        for(Todo todo : todos){
            todorepository.deleteById(todo.getId());
        }
    }

    public void delete(Long id){
        listservice.deleteByIdlist(id);
        listrepository.deleteById(id);
    }
    public ListModel get(Long id){
        return listrepository.findById(id).orElseThrow();
    }

}
