package com.app.server.service.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordPolicy;

import com.app.server.repository.appbasicsetup.usermanagement.ArtPasswordPolicyRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "Service for AppMenus Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/ArtPasswordPolicy")
public class ArtPasswordPolicyServiceImpl extends ArtPasswordPolicyService {
	private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private ArtPasswordPolicyRepository<ArtPasswordPolicy> artPasswordPolicyrepository;

	/**
     * Retrieve list of  <ArtPasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
	@RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public HttpEntity<ResponseBean> findAll() throws Exception {
		java.util.List<ArtPasswordPolicy> lstappmenus = artPasswordPolicyrepository.findAll();
		AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
		responseBean.add("data", lstappmenus);
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "findAll", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	/**
     * Saves the new  <ArtPasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- ArtPasswordPolicy
     * @throws java.lang.Exception
     */
	@RequestMapping(consumes = "application/json", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody ArtPasswordPolicy entity) throws Exception {
		artPasswordPolicyrepository.save(entity);
		AppAlarm appAlarm = Log.getAlarm("ABSUM122990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		responseBean.add("data", entity);
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "save", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	/**
     * Saves the new  list of new <ArtPasswordPolicy> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<ArtPasswordPolicy>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws Exception {
		artPasswordPolicyrepository.save(entity);
		AppAlarm appAlarm = Log.getAlarm("ABSUM122990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "save", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	/**
     * Deletes the   <ArtPasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
	@RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
	@Override
	public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
		artPasswordPolicyrepository.delete(entity);
		AppAlarm appAlarm = Log.getAlarm("ABSUM128990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "delete", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	/**
     * Updates the  <ArtPasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- ArtPasswordPolicy
     * @throws java.lang.Exception
     */
	@RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody ArtPasswordPolicy entity) throws Exception {
		artPasswordPolicyrepository.update(entity);
		AppAlarm appAlarm = Log.getAlarm("ABSUM128990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		responseBean.add("data", entity._getPrimarykey().toString());
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "update", "ArtPasswordPolicyServiceImpl");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	/**
     * Updates the list of  <ArtPasswordPolicy> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<ArtPasswordPolicy>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws Exception {
		artPasswordPolicyrepository.update(entity);
		AppAlarm appAlarm = Log.getAlarm("ABSUM121990200");
		ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "update", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

	 /**
     * Retrieves <ArtPasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
		ArtPasswordPolicy lstappmenus = artPasswordPolicyrepository.findById((java.lang.String) findByBean.getFindKey());
		AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
		com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
		responseBean.add("success", true);
		responseBean.add("message", String.format(appAlarm.getMessage(), "ArtPasswordPolicy"));
		responseBean.add("data", lstappmenus);
		Log.out.println("ABSUM124990200", runtimeLogInfoHelper.getRequestHeaderBean(), "ArtPasswordPolicyServiceImpl", "save", "ArtPasswordPolicy");
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
	}

}
