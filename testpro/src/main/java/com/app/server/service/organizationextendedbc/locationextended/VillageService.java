package com.app.server.service.organizationextendedbc.locationextended;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organizationextendedbc.locationextended.Village;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Service for Village Master table Entity", complexity = Complexity.LOW)
public abstract class VillageService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(Village entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Village> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Village entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Village> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByCountryId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByStateId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByRegionId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByDistrictId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByTalukaaId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
