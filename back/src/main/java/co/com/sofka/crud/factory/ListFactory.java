package co.com.sofka.crud.factory;

import co.com.sofka.crud.dtos.ListDTO;
import co.com.sofka.crud.model.ListModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListFactory {

    private TodoFactory todoFactory;



    public ListDTO toListDTO(ListModel list){
        ListDTO listDTO = new ListDTO();
        listDTO.setIdlist(list.getId());
        listDTO.setName(list.getName());

        if(listDTO.getTodos() != null)
            listDTO.setTodos(todoFactory.toTodoList(list.getTodos()));

        return listDTO;
    }

    public List<ListDTO> toListDTO(List<ListModel> list){
           List<ListDTO> listDTO = list.stream().map(this::toListDTO).collect(Collectors.toList());

           return listDTO;


    }

    public ListModel toListTodos(ListDTO listDTO){
       ListModel list = new ListModel();

        list.setId(listDTO.getIdlist());
        list.setName(listDTO.getName());
        list.setTodos(new ArrayList<>());

        return list;
    }
}
