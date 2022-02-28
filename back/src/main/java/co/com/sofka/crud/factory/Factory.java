package co.com.sofka.crud.factory;

import co.com.sofka.crud.dtos.TodoDTO;
import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Factory {



    //List
    public ListDTO toListDTO(ListModel list){
        ListDTO listDTO = new ListDTO();
        listDTO.setIdlist(list.getId());
        listDTO.setName(list.getName());

        if(listDTO.getTodos()!= null) {

            listDTO.setTodos(toTodoList(list.getTodos()));
        }
        return listDTO;
    }

    public List<ListDTO> toListsDTO(List<ListModel> list){
        List<ListDTO> listDTO;
        listDTO = list.stream().map(this::toListDTO).collect(Collectors.toList());

           return listDTO;


    }

    public ListModel toListTodos(ListDTO listDTO){
       ListModel list = new ListModel();

        list.setId(listDTO.getIdlist());
        list.setName(listDTO.getName());
        list.setTodos(new ArrayList<>());

        return list;
    }
    /*******************************************************************************************************************/
    //TO-DO
    public TodoDTO toTodoDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();
        System.out.println("ACA TOy 3");
        System.out.println(todo.getName());
        todoDTO.setId(todo.getId());
        todoDTO.setCompleted(todo.isCompleted());
        todoDTO.setName(todo.getName());

        if(todo.getList() != null)
            todoDTO.setIdlist(todo.getList().getId());

        return todoDTO;
    }

    public List<TodoDTO> toTodoList(List<Todo> todo){
        System.out.println("ACA toy2");
        System.out.println(todo.get(0).getName());

            List<TodoDTO> todoDTO = todo.stream().map(this::toTodoDTO).collect(Collectors.toList());


            return todoDTO;


    }

    public Todo toTodoModel(TodoDTO todoDTO){
        Todo todo = new Todo();
        todo.setId(todoDTO.getId());
        todo.setName(todoDTO.getName());
        todo.setCompleted(todoDTO.isCompleted());

        return todo;
    }
}
