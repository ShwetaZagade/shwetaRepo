package com.app.server.repository.appinsight.alarms;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.appinsight.health.TestA;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Repository for AEntity Transaction table", complexity = Complexity.MEDIUM)
public interface AEntityRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    public void deleteTestA(List<TestA> testa);

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    T findById(String aid) throws Exception;
}
