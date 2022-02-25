package co.com.sofka.crud.service;

import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.ListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListService {
    @Autowired
    private ListRepository listrepository;
    @Autowired
    private TodoRepository todorepository;


    public Iterable<ListModel> list(){
        return listrepository.findAll();
    }
    public ListModel save(ListModel list){
        return listrepository.save(list);
    }
    public void delete(Long id){
        todorepository.deleteByIdlist(id);
        listrepository.deleteById(id);
    }
    public ListModel get(Long id){
        return listrepository.findById(id).orElseThrow();
    }

}
