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

    public ListDTO toGroupDTO(ListModel list){
        ListDTO groupDTO = new ListDTO();
        groupDTO.setIdlist(list.getId());
        groupDTO.setName(list.getName());

        if(groupDTO.getTodos() != null)
            groupDTO.setTodos(todoFactory.toTodoList(list.getTodos()));

        return groupDTO;
    }

    public List<ListDTO> toGroupsDTO(List<ListModel> list){
        List<ListDTO> listDTO = list.stream().map(this::toGroupDTO).collect(Collectors.toList());

        return listDTO;
    }

    public ListModel toGroupTodos(ListDTO listDTO){
       ListModel list = new ListModel();

        list.setId(listDTO.getIdlist());
        list.setName(listDTO.getName());
        list.setTodos(new ArrayList<>());

        return list;
    }
}
