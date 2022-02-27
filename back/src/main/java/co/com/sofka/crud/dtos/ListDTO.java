package co.com.sofka.crud.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListDTO {

    private long idlist;
    private String name;
    private List<TodoDTO> todos = new ArrayList<>();

    public ListDTO() {
    }


    public long getIdlist() {
        return idlist;
    }

    public void setIdlist(long idlist) {
        this.idlist = idlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDTO> todos) {
        this.todos = todos;
    }
}
