package me.edurevsky.controleescola.services.utils;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;

public class Handlers {
    
    public static <T1, T2> void handleEntityNotFound(CrudRepository<T1, T2> repository, T2 id, String msg) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(msg);
        }
    }

}
