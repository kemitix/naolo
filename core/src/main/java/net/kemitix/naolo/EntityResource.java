package net.kemitix.naolo;

import net.kemitix.naolo.storage.HasId;

import javax.ws.rs.core.Response;
import java.util.List;

public interface EntityResource<T extends HasId> {
    List<T> all();

    T get(long id);

    Response add(T entity);

    T update(long id, T entity);

    T remove(long id);
}
