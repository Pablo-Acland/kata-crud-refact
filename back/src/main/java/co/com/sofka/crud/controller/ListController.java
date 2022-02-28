package co.com.sofka.crud.controller;


import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/List")
public class ListController {

    @Autowired
    private ListService listservice;

    @GetMapping(value = "/Lists")
    public List<ListDTO> list(){
        return listservice.list();}

    @GetMapping(value = "/{Idlist}")
    public ListDTO getGroupById(@PathVariable("Idlist") Long Idlist){
        return listservice.getListId(Idlist);
    }

    @PostMapping(value = "/save")
    public ListDTO save(@RequestBody ListDTO listDTO){
        return listservice.saveList(listDTO);
    }

    @PutMapping(value = "/update/{Idlist}")
    public TodoDTO update(@PathVariable("Idlist") Long Idlist, @RequestBody TodoDTO todo){
        if(todo.getIdlist() != null){
            return listservice.updateTodoByIdList(Idlist, todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @PostMapping(value = "/save/{Idlist}")
    public TodoDTO saveTodoByIdList(@PathVariable("Idlist") Long Idlist, @RequestBody TodoDTO todoDTO) {
        return listservice.saveTodoByIdList(Idlist, todoDTO);
    }
    @PutMapping(value = "/update/todo/{Idlist}")
    public TodoDTO updateTodoByIdList(@PathVariable("Idlist") Long Idlist, @RequestBody TodoDTO todoDTO){
        if(todoDTO.getId() != null){
            return listservice.updateTodoByIdList(Idlist, todoDTO);
        }
        throw new RuntimeException("El ToDo a actualizar debe contener un id");
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        listservice.deleteListById(id);
    }

}
