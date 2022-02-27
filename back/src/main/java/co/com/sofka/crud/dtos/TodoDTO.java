package co.com.sofka.crud.dtos;

public class TodoDTO {
    private Long id;
    private String name;
    private boolean completed;
    private Long Idlist;

    public TodoDTO() {
    }

    public TodoDTO(Long id, String name, boolean completed, Long Idlist) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.Idlist = Idlist;
    }

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

    public Long getIdlist() {
        return Idlist;
    }

    public void setIdlist(Long idlist) {
        Idlist = idlist;
    }
}
