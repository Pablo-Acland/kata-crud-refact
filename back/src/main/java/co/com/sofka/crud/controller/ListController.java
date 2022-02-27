package co.com.sofka.crud.controller;


import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.service.ListService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/List")
public class ListController {

    private ListService listservice;

    @GetMapping(value = "/all")
    public List<ListDTO> list(){return listservice.list();}

    @PostMapping(value = "/save")
    public ListDTO save(@RequestBody ListDTO list){
        return listservice.saveList(list);
    }

    @PutMapping(value = "/update")
    public ListDTO update(@RequestBody ListDTO List){
        if(List.getId() != null){
            return listservice.saveList(List);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id")Long id){
        listservice.delete(id);
    }

}
