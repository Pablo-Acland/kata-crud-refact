package co.com.sofka.crud.service;

import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.factory.Factory;
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
    private Factory factory;


    public List<ListDTO> list(){
        List<ListModel> list = (List<ListModel>) listrepository.findAll();
        System.out.println("ACA TOY");
        System.out.println(list.get(0).getName());
        return factory.toListsDTO(list);
    }
    public ListDTO getListId(Long Idlist){
        ListModel list = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("No existe el id "));
        return factory.toListDTO(list);
    }

    public ListDTO saveList(ListDTO listDTO) {
        if(listDTO.getName().trim().isEmpty()){
            throw new RuntimeException("El nombre es necesario ");
        }
        ListModel list = factory.toListTodos(listDTO);
        listDTO = factory.toListDTO(listrepository.save(list));
        return listDTO;
    }

    public void deleteListById(Long Idlist){
        ListModel todo = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));

        listrepository.deleteById(Idlist);

    }

    public TodoDTO saveTodoByIdList(Long Idlist, TodoDTO todoDTO) {
        if(todoDTO.getName().trim().isEmpty()){
            throw new RuntimeException("Campo nombre no puede estár vacío");
        }

        ListModel list = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));
        Todo todo = factory.toTodoModel(todoDTO);
        todo.setList(list);
        todoDTO = factory.toTodoDTO(todorepository.save(todo));

        return todoDTO;
    }

    public TodoDTO updateTodoByIdList(Long Idlist, TodoDTO todoDTO) {

        ListModel list = listrepository.findById(Idlist).orElseThrow(() -> new RuntimeException("El id no existe"));
        todorepository.findById(todoDTO.getId()).orElseThrow(() -> new RuntimeException("El id no existe"));
        Todo todo = factory.toTodoModel(todoDTO);
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
        todoDTO = factory.toTodoDTO(todo);

        return todoDTO;
    }

    public void deleteTodoById(Long Idtodo) {
        // Se valida la existencia de un To do con el id enviado
        Todo todo = todorepository.findById(Idtodo)
                .orElseThrow(() -> new RuntimeException("El id no existe"));

        todorepository.delete(todo);
    }

}
