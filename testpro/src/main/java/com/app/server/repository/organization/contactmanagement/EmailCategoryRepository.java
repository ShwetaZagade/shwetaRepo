package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for EmailCategory Master table Entity", complexity = Complexity.LOW)
public interface EmailCategoryRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String emailCatId) throws Exception;
}
