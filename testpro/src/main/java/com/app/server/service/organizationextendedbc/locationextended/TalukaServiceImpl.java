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
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;
import com.app.shared.organizationextendedbc.locationextended.Taluka;
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
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Service for Taluka Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/Taluka")
public class TalukaServiceImpl extends TalukaService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private TalukaRepository<Taluka> talukarepo;

    /**
     * Retrieve list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<Taluka> lsttaluka = talukarepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findAll", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Taluka
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Taluka entity) throws Exception {
        talukarepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "save", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <Taluka> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Taluka>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Taluka> entity, @RequestHeader("isArray") boolean request) throws Exception {
        talukarepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "save", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        talukarepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "delete", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- Taluka
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Taluka entity) throws Exception {
        talukarepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "update", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <Taluka> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<Taluka>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Taluka> entity, @RequestHeader("isArray") boolean request) throws Exception {
        talukarepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("OEBLE123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "update", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params fieldData type :- Map<String,Object>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lsttaluka = talukarepo.search("Taluka.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "search", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<String, String> fieldMetaData = new java.util.HashMap<String, String>();
        fieldMetaData.put("countryId", "String");
        fieldMetaData.put("stateId", "String");
        fieldMetaData.put("regionId", "String");
        fieldMetaData.put("districtId", "String");
        fieldMetaData.put("talukaName", "String");
        fieldMetaData.put("talukaCode", "String");
        return fieldMetaData;
    }

    /**
     * Retrieves list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByCountryId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByCountryId(@RequestBody FindByBean findByBean) throws Exception {
        List<Taluka> lsttaluka = talukarepo.findByCountryId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findByCountryId", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByStateId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByStateId(@RequestBody FindByBean findByBean) throws Exception {
        List<Taluka> lsttaluka = talukarepo.findByStateId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findByStateId", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByRegionId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByRegionId(@RequestBody FindByBean findByBean) throws Exception {
        List<Taluka> lsttaluka = talukarepo.findByRegionId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findByRegionId", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByDistrictId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByDistrictId(@RequestBody FindByBean findByBean) throws Exception {
        List<Taluka> lsttaluka = talukarepo.findByDistrictId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findByDistrictId", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <Taluka> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        Taluka lsttaluka = talukarepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("OEBLE124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Taluka"));
        responseBean.add("data", lsttaluka);
        Log.out.println("OEBLE124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TalukaServiceImpl", "findById", "Taluka");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
