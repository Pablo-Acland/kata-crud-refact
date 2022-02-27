package co.com.sofka.crud.service;

import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.factory.ListFactory;
import co.com.sofka.crud.factory.TodoFactory;
import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.repository.ListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListService {

    @Autowired
    private ListRepository listrepository;
    @Autowired
    private TodoRepository todorepository;
    @Autowired
    private ListFactory listfactory;

    @Autowired
    private TodoFactory todosfactory;


    public List<ListDTO> list(){
        List<ListModel> list = (List<ListModel>) listrepository.findAll();

        return listfactory.toGroupsDTO(list);
    }
    public ListDTO getListId(Long list){
        ListModel group = listrepository.findById(list).orElseThrow(() -> new RuntimeException("No existe el id para actualziar"));
        return listfactory.toGroupDTO(group);
    }

    public ListDTO saveList(ListDTO listDTO) {
        if(listDTO.getName().trim().isEmpty()){
            throw new RuntimeException("El nombre es necesario ");
        }
        ListModel list = listfactory.toGroupTodos(listDTO);
        listDTO = listfactory.toGroupDTO(listrepository.save(list));
        return listDTO;
    }

    public void deleteListById(Long Idlist){
        ListModel todo = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));

        listrepository.deleteById(Idlist);

    }

    public TodoDTO saveListId(Long Idlist, TodoDTO todoDTO) {
        if(todoDTO.getName().trim().isEmpty()){
            throw new RuntimeException("Campo nombre no puede estár vacío");
        }

        ListModel list = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));
        Todo todo = todosfactory.toTodoModel(todoDTO);
        todo.setList(list);
        todoDTO = todosfactory.toTodoDTO(todorepository.save(todo));

        return todoDTO;
    }

    public TodoDTO updateTodoByList(Long Idlist, TodoDTO todoDTO) {

        ListModel list = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));
        todorepository.findById(todoDTO.getId()).orElseThrow(() -> new RuntimeException("El id no existe"));
        Todo todo = todosfactory.toTodoModel(todoDTO);
        todo.setList(list);

        list.getTodos().stream().forEach((i) -> {
            if(i.getId() == todo.getId()){
                i.setName(todo.getName());
                i.setCompleted(todo.isCompleted());
                i.setId(todo.getId());
            }
        });


        todo.setList(list);
        listrepository.save(list);
        todoDTO = todosfactory.toTodoDTO(todo);

        return todoDTO;
    }

    public void deleteTodoById(Long Idtodo) {
        // Se valida la existencia de un To do con el id enviado
        Todo todo = todorepository.findById(Idtodo)
                .orElseThrow(() -> new RuntimeException("El id no existe"));

        todorepository.delete(todo);
    }

}
