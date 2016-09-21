package com.app.server.repository.organizationextendedbc.locationextended;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for AddressExtended Transaction table", complexity = Complexity.MEDIUM)
public interface AddressExtendedRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByAddressId(String addressId) throws Exception;

    List<T> findByVillageId(String villageId) throws Exception;

    List<T> findByTalukaId(String talukaId) throws Exception;

    List<T> findByDistrictId(String districtId) throws Exception;

    List<T> findByRegionId(String regionId) throws Exception;

    T findById(String addExtId) throws Exception;
}
