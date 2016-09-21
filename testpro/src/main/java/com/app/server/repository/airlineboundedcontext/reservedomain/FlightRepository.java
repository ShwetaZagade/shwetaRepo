package com.app.server.repository.airlineboundedcontext.reservedomain;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Repository for Flight Transaction table", complexity = Complexity.MEDIUM)
public interface FlightRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByFlySource(String flySource) throws Exception;

    List<T> findByFlyDestination(String flyDestination) throws Exception;

    T findById(String flightId) throws Exception;
}
