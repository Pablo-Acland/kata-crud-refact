package co.com.sofka.crud.repository;

import co.com.sofka.crud.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Iterable<Todo> findByIdlist(long Idlist);
    Todo deleteByIdlist(long Idlist);
}
