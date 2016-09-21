package com.app.server.service.airlineboundedcontext.reservedomain;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.airlineboundedcontext.reservedomain.Flight;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Service for Flight Transaction table", complexity = Complexity.MEDIUM)
public abstract class FlightService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Flight entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Flight> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Flight entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Flight> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByFlySource(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByFlyDestination(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
