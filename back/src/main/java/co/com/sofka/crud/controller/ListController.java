package co.com.sofka.crud.controller;


import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import co.com.sofka.crud.service.ListService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/List")
public class ListController {

    private ListService listservice;

    @GetMapping(value = "/all")
    public Iterable<ListModel> Todos(){return listservice.list();}

    @PostMapping(value = "/save")
    public ListModel save(@RequestBody ListModel list){
        return listservice.save(list);
    }

    @PutMapping(value = "/update")
    public ListModel update(@RequestBody ListModel List){
        if(List.getId() != null){
            return listservice.save(List);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id")Long id){
        listservice.delete(id);
    }

}
