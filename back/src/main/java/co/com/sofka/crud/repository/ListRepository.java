package co.com.sofka.crud.repository;

import co.com.sofka.crud.model.ListModel;
import co.com.sofka.crud.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CrudRepository<ListModel, Long> {
}
