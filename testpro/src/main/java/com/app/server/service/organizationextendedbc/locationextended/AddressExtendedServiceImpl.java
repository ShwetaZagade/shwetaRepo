package com.app.server.service.organizationextendedbc.locationextended;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.organizationextendedbc.locationextended.AddressExtendedRepository;
import com.app.shared.organizationextendedbc.locationextended.AddressExtended;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Service for AddressExtended Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/AddressExtended")
public class AddressExtendedServiceImpl extends AddressExtendedService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private AddressExtendedRepository<AddressExtended> addressExtendedrepo;

    /**
     * Retrieve list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<AddressExtended> lstaddressextended = addressExtendedrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findAll", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- AddressExtended
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody AddressExtended entity) throws Exception {
        addressExtendedrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "save", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <AddressExtended> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<AddressExtended>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<AddressExtended> entity, @RequestHeader("isArray") boolean request) throws Exception {
        addressExtendedrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "save", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        addressExtendedrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "delete", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- AddressExtended
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody AddressExtended entity) throws Exception {
        addressExtendedrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "update", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <AddressExtended> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<AddressExtended>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<AddressExtended> entity, @RequestHeader("isArray") boolean request) throws Exception {
        addressExtendedrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "update", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByAddressId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByAddressId(@RequestBody FindByBean findByBean) throws Exception {
        List<AddressExtended> lstaddressextended = addressExtendedrepo.findByAddressId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findByAddressId", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByVillageId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByVillageId(@RequestBody FindByBean findByBean) throws Exception {
        List<AddressExtended> lstaddressextended = addressExtendedrepo.findByVillageId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findByVillageId", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByTalukaId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByTalukaId(@RequestBody FindByBean findByBean) throws Exception {
        List<AddressExtended> lstaddressextended = addressExtendedrepo.findByTalukaId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findByTalukaId", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByDistrictId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByDistrictId(@RequestBody FindByBean findByBean) throws Exception {
        List<AddressExtended> lstaddressextended = addressExtendedrepo.findByDistrictId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findByDistrictId", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByRegionId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByRegionId(@RequestBody FindByBean findByBean) throws Exception {
        List<AddressExtended> lstaddressextended = addressExtendedrepo.findByRegionId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findByRegionId", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AddressExtended> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        AddressExtended lstaddressextended = addressExtendedrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddressExtended"));
        responseBean.add("data", lstaddressextended);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedServiceImpl", "findById", "AddressExtended");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
