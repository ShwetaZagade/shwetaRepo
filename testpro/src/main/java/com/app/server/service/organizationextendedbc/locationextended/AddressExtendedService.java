package com.app.server.service.organizationextendedbc.locationextended;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organizationextendedbc.locationextended.AddressExtended;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Service for AddressExtended Transaction table", complexity = Complexity.MEDIUM)
public abstract class AddressExtendedService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> save(AddressExtended entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<AddressExtended> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(AddressExtended entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<AddressExtended> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByAddressId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByVillageId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByTalukaId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByDistrictId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByRegionId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
