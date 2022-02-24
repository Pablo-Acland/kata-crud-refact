package co.com.sofka.crud.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "listId")
    private Set<Todo> todos = new HashSet<Todo>();

    public ListModel() { }

    public ListModel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ListModel)) {
            return false;
        }
        ListModel listModel = (ListModel) o;
        return Objects.equals(id, listModel.id) && Objects.equals(name, listModel.name) && Objects.equals(todos, listModel.todos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todos);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", todos='" + getTodos() + "'" +
                "}";
    }
}
