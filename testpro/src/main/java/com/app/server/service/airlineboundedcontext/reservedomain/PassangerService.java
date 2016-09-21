package com.app.server.service.airlineboundedcontext.reservedomain;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.airlineboundedcontext.reservedomain.Passanger;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "5", comments = "Service for Passanger Transaction table", complexity = Complexity.MEDIUM)
public abstract class PassangerService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Passanger entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Passanger> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Passanger entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Passanger> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByPassangerAddr(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
