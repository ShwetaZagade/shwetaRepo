package com.app.server.service.appinsight.alarms;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.appinsight.alarms.AEntity;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Service for AEntity Transaction table", complexity = Complexity.MEDIUM)
public abstract class AEntityService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(AEntity entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<AEntity> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(AEntity entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<AEntity> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
