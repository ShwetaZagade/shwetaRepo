package com.app.server.service.multitenant.customers;
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
import com.app.server.repository.multitenant.customers.AppCustomerCategoryRepository;
import com.app.shared.multitenant.customers.AppCustomerCategory;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Service for AppCustomerCategory Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/AppCustomerCategory")
public class AppCustomerCategoryServiceImpl extends AppCustomerCategoryService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private AppCustomerCategoryRepository<AppCustomerCategory> appCustomerCategoryrepo;

    /**
     * Retrieve list of  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<AppCustomerCategory> lstappcustomercategory = appCustomerCategoryrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("MBCCU124900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", lstappcustomercategory);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "findAll", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- AppCustomerCategory
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody AppCustomerCategory entity) throws Exception {
        appCustomerCategoryrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("MBCCU122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "save", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <AppCustomerCategory> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<AppCustomerCategory>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<AppCustomerCategory> entity, @RequestHeader("isArray") boolean request) throws Exception {
        appCustomerCategoryrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("MBCCU122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "save", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        appCustomerCategoryrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("MBCCU128900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "delete", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- AppCustomerCategory
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody AppCustomerCategory entity) throws Exception {
        appCustomerCategoryrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("MBCCU121900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "update", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <AppCustomerCategory> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<AppCustomerCategory>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<AppCustomerCategory> entity, @RequestHeader("isArray") boolean request) throws Exception {
        appCustomerCategoryrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("MBCCU121900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "update", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve list of  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params fieldData type :- Map<String,Object>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lstappcustomercategory = appCustomerCategoryrepo.search("AppCustomerCategory.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("MBCCU124900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", lstappcustomercategory);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "search", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<String, String> fieldMetaData = new java.util.HashMap<String, String>();
        fieldMetaData.put("customerCategory", "String");
        return fieldMetaData;
    }

    /**
     * Retrieves list of  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findByAppCustTypeId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByAppCustTypeId(@RequestBody FindByBean findByBean) throws Exception {
        List<AppCustomerCategory> lstappcustomercategory = appCustomerCategoryrepo.findByAppCustTypeId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("MBCCU124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", lstappcustomercategory);
        Log.out.println("MBCCU124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "findByAppCustTypeId", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieves list of  <AppCustomerCategory> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        AppCustomerCategory lstappcustomercategory = appCustomerCategoryrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("MBCCU124900200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AppCustomerCategory"));
        responseBean.add("data", lstappcustomercategory);
        Log.out.println("MBCCU124900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerCategoryServiceImpl", "findById", "AppCustomerCategory");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
