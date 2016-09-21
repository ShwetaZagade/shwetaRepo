package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.ZonesRepository;
import com.app.shared.organizationextendedbc.locationextended.Zones;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class ZonesTestCase extends EntityTestCriteria {

    /**
     * ZonesRepository Variable
     */
    @Autowired
    private ZonesRepository<Zones> zonesRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Zones createZones(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("j05luXInhTVnozS8HXUmu9LGn5wLu99jVQNYEeW6Owwh6r5Urk");
        Country country = new Country();
        country.setCapitalLatitude(7);
        country.setCountryCode1("Clq");
        country.setIsoNumeric(254);
        country.setCurrencySymbol("tfR1ZYfOi7IUPC3x1M9A0umXGk8H3fMh");
        country.setCountryName("41sBDis1Aofb0Vmb4L9Ey2bjUpvJXP2mX25kz9tuiUYAD6vrDx");
        country.setCurrencyCode("lMI");
        country.setCountryCode2("9Cd");
        country.setCapitalLongitude(7);
        country.setCapital("AQzycOLIXsVsxdRQfWvKuIi1jefvlQWZ");
        country.setCurrencyName("Z4PoTbd0pUAbwXqlZ0vgNrmUAafTRKTGgnjiXZ3GYXnAmQ0XHV");
        country.setCountryFlag("nFoD0tx6HNi0paiNIyxsXopQdMRcemseTo7Dr0hIqpsflXX2dq");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("wcQbDH1s7YQyrR6AunMRsq0lHNB4ihRAL57wfPKWqSmH1BO9Ju");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("KomGcrzD9ja6zF0WY0BNHHDOP6G5EXAVao87SA1byZIHBJJ0Ks");
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar3("jGiApslYZ59XuzQw3IT1rTspTjaGeWJj");
        state.setStateCode(2);
        state.setStateCodeChar2("Wu2lsvrmUHOhiIQFejDbvm0IvnfO0YbU");
        state.setStateCapital("8BeQJQHxM7tjOF6TFzkRsvOgGg9SctArRlnEBEhBObYNDtY062");
        state.setStateFlag("SqSK0rGCBJIdWDNLY6yaT9GPCLaPhhpKSYH8BCuGculz8pMnoz");
        state.setStateCapitalLongitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Zones zones = new Zones();
        zones.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        zones.setZoneName("vNLRQebt1ilsPhUR89Wje6XH4tGGZw0Lda8jIqMlzRpsIe89Zy");
        zones.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        zones.setEntityValidator(entityValidator);
        return zones;
    }

    @Test
    public void test1Save() {
        try {
            Zones zones = createZones(true);
            zones.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            zones.isValid();
            zonesRepository.save(zones);
            map.put("ZonesPrimaryKey", zones._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("ZonesPrimaryKey"));
            Zones zones = zonesRepository.findById((java.lang.String) map.get("ZonesPrimaryKey"));
            zones.setVersionId(1);
            zones.setZoneName("lmABhqfSIppUcsYckkQucibjgAmhEiuLbKlDigfCQhQjbdhP3U");
            zones.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            zonesRepository.update(zones);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("ZonesPrimaryKey"));
            zonesRepository.findById((java.lang.String) map.get("ZonesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Zones> listofstateId = zonesRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Zones> listofcountryId = zonesRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("ZonesPrimaryKey"));
            zonesRepository.delete((java.lang.String) map.get("ZonesPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateZones(EntityTestCriteria contraints, Zones zones) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            zones.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            zones.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            zones.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            zonesRepository.save(zones);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "zoneName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "zoneName", "b1twhL5i0nriHVOsHJn4D4Alf5IHJZ9abXiYmWt8Io3QX5Uw4XxDITws9aeklfQBB39zxEq0VQpIpse8tdU74oRXjAcCN6AIbEKBzGLpvsqL8tMtK7Rai6TUbxwDV9e2XsthaUSW0stQglnb3TBNjGyebJN7Pu8sAhSt3DHDMpJl0zilNddVPJ0Qnts0k8AYyXelvTVUDzgJzsS8qtqoHCnvaHUXM2i2f2V12RARgkTQj3U96ljcXsYda00Tz5m0j"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Zones zones = createZones(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = zones.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(zones, null);
                        validateZones(contraints, zones);
                        failureCount++;
                        break;
                    case 2:
                        zones.setZoneName(contraints.getNegativeValue().toString());
                        validateZones(contraints, zones);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
