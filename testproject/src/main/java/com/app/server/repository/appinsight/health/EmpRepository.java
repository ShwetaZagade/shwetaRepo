package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.appinsight.health.EmpDEpt;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "4", comments = "Repository for Emp Transaction table", complexity = Complexity.MEDIUM)
public interface EmpRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deleteEmpDEpt(List<EmpDEpt> empdept);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String empId) throws Exception;
}
