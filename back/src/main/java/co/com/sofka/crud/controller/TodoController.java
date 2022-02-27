package co.com.sofka.crud.controller;

import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService todoservice;


    @GetMapping(value = "/api/all")
    public List<TodoDTO> Todos(){return todoservice.list();}



}
