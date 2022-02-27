package co.com.sofka.crud.model;


import javax.persistence.*;


@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean completed;


    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private ListModel list;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ListModel getList() {
        return list;
    }

    public void setList(ListModel list) {
        this.list = list;
    }
}
