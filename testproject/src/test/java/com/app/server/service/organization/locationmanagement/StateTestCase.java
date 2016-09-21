package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(10);
        country.setCountryName("EV3BgBkLDfqJtCCjdcJy6SrlkkXNqFTTEZmSHzwxPhp5QnkcX4");
        country.setCapitalLatitude(7);
        country.setIsoNumeric(404);
        country.setCurrencyName("6ggToOPAsdK3wdoPgCJK63xQgjSN2ytyOucrqUMJIRSOmtI6wO");
        country.setCurrencySymbol("WU6h9tXOwF8yJ4EzKy1hxLZhHDKhvuk9");
        country.setCapital("hr0LG4NbERngfAZfMKopWIDSNpdJtpoX");
        country.setCountryCode2("57t");
        country.setCurrencyCode("BLH");
        country.setCountryFlag("OaYETclufrfPbBiOJvBiaxUus5m4E346O2d9Xg7LxIvepRJ13U");
        country.setCountryCode1("lVe");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar3("iX7UkblMlx0MCBMPdboOMcYH8Kpz6BA6");
        state.setStateCapital("iI7UDRuwGTIloaGy3TXhv8yKOW6K944zmFfNwjqAM7SedQg2ga");
        state.setStateFlag("cYdi9BbZVnwwk8HsxalJjgnOGombvrxQj0Xq6pTTHAngZBwr7f");
        state.setStateDescription("tkihi8lHX36e6Uo4xiMW5c6BIrq6aD1hBTX9xlnyiQwUN4itif");
        state.setStateName("ua3A6uszU7ssf5T7js2X7AJ0RPxUOwebzvLnXaaacAYWgUHk00");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapitalLatitude(9);
        state.setStateCodeChar2("xxnvB5O6wfiRcS8dQlqngD3KnpICMoxy");
        state.setStateCapitalLongitude(7);
        state.setStateCode(2);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setVersionId(1);
            state.setStateCodeChar3("27knNZtcYdjUnpCUlI40gY2771hEFjqp");
            state.setStateCapital("iy4TtKcANcv7K9OWxiDAPwA8xmFrKUTYnEjkfuY0Q4llMBx1up");
            state.setStateFlag("5MkKkxV8YntDXC1EjIcE5n45HQ4pz64GoKEia1jdcwoTk2gUYW");
            state.setStateDescription("aTEAB5nEYxMRPwBakhPsOeO4eVzCkE7jcnPQIIcWtMvYuRU0dm");
            state.setStateName("WG4LAfj7z5UTDfXw9FRquQ4RAgTfLapDGqzNWPyYmroYB28tHW");
            state.setStateCapitalLatitude(2);
            state.setStateCodeChar2("glNTvM5myBjORkPzNSQibjLnGd7mdJyB");
            state.setStateCapitalLongitude(11);
            state.setStateCode(2);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "NDEJTtQcxL8y0ci3y4h5SqRbW2KlnuNBHatSnXK2UhsiVkCoNwVIMHFacQN86l8WEIyUSJ6pSIRk60PVo5UcvVOzZrIikVRBiA6yXChz9RG0Khsq5Tpdgjyz3iRhjSrT92liBu5yPyGOFH6TYqqIRPPt2GTqchrzn0NJfFBhuJqPz14v5dZVzMk6mWvCfq2AR94S3CIsqzuvK48YnKtp3tqzjHBT899J3eiZQAQKDqxFTSG7Chm3ZSczcohYW0cFn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "PvlZDM1yjSR0V3OmlwPMNXDnidzcn0f1C"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "zXNRncJwHo4EjNBRQ1x73UEw9TaO4gx7z"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "lb20jI3IqdmIZnnutlXznTZfSwaUOBjg1J0hzzfA75REcnJw3XdTEYfxTNgKeddjhoJInqH8ljIcatYiLOVIIIVAohaHggJgTBKJeOPDxtajuBqnLVe7lVDktS8b1Yd1qLOXBfT6maf8LtLLoqPwyNhMufIYBtqQjZF83HAuI4bDmJGcRmxKL0yIJQusnkxlQhZ8JimAvyd5eRIbAyCKdwnYIO6hRXcLC0YMcCKouxs7LqN2UiOjM5iqLFhNtOP0g"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "5Qn3gMkKPIhCSTgafa38FLIk8Jk0rkMq5LwgQGl1LcilBldSTv3cP5dKmpOAWA2RhgtkXDdBQcDPr2VnGSuvXEMZx4RR92qDL4LfYhNeNXqzk82rw9sQzCpcUKDs2s5iU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "zPWiXH3BIIATrk07Ia6YUGVKgEuP4hlfZJXxqrivfqqYuMY0utA8ZXxaNXwJvqa5OcrA6yDHYd2BFmp70LFxR2eZ72hSZnKlMtUvF2lqYzxwI1qjED3bKE7M25zemgX2X"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 18));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
