package co.com.sofka.crud.service;

import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.ListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("TodoService")
public class TodoService {

    @Autowired
    private TodoRepository todorepository;
    @Autowired
    private ListRepository listrepository;


    public Iterable<Todo> list(){
        return todorepository.findAll();
    }
    public Todo update(Todo todo){
        return todorepository.save(todo);
    }
    public Todo save(Todo todo, Long Idlist){
        ListModel listodo = listrepository.findById(Idlist).orElseThrow();
        todo.setList(listodo);
        return todorepository.save(todo);
    }

    public void delete(Long id){
        todorepository.delete(getId(id));
    }

    public Todo getId(Long id){
         return todorepository.findById(id).orElseThrow();
    }

    public List<Todo> getIdlist(ListModel list){ return todorepository.findByIdlist(list.getId());}



}
