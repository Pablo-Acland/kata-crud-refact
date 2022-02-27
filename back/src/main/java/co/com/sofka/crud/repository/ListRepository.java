package co.com.sofka.crud.repository;

import co.com.sofka.crud.model.ListModel;
import org.springframework.data.repository.CrudRepository;

public interface ListRepository extends CrudRepository<ListModel, Long> {
}
