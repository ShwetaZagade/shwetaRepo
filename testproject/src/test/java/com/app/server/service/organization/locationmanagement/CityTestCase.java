package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCodeChar3("8XMIJTUvBIZCYLkuTfGdIR4M6LzP8GVf");
        state.setStateCapital("9uGoHwMCqSbxdzYw1A1RpYVu0ynDB7lNyoPCbmNc2dRrBDuI8Z");
        state.setStateFlag("sAHllYA50xBcUZ7agenMghMBPh3X7IVjMlck6MjyGWgfpJbDLZ");
        state.setStateDescription("5MXRAc9alacBdyibYYYv290nChGLYfrZjIt4tTKzKe9x260EDs");
        state.setStateName("ahq38jdz2In997HTg2oHqM862vlsp6No8CMHjyTDqc3Q4UxqsR");
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCountryName("XPydXYkiJK3nUExboijSbAP4nZNixMH4t0B3qsKw1xncxah3V0");
        country.setCapitalLatitude(1);
        country.setIsoNumeric(657);
        country.setCurrencyName("rsIXe9w7IIy81LKVSXO2wzL6VLZWeLLIFoGsV0gdT9adUO0w6E");
        country.setCurrencySymbol("MfDY7FMgbEELrNlint9EK2fyTN4YmFXj");
        country.setCapital("GRNLcEeA0iloKfcxGeQCQbtC3sBzPCI5");
        country.setCountryCode2("Mqg");
        country.setCurrencyCode("LPi");
        country.setCountryFlag("E9Ck7okQf5EegSbdm2NjAEAPa7seal7oZ54SPkUfYpjwsMgGcb");
        country.setCountryCode1("NCj");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("h1bQm51cifice3kYQWMU05gQkWaviPA3");
        state.setStateCapital("JnubOGVGrvAmFVT3hoTC9quwbVL1qYkEmhUwdC9bVghQeQpsob");
        state.setStateFlag("DFQIAmoMVrptWuV79jC6sZFj41lH2vlujyUUs2p3Nmw3O9CIe9");
        state.setStateDescription("7eG0Fxu99QxpFcWcmrRwBrHDCvtqUtomOSLOwOHFY4C2xkOZ16");
        state.setStateName("tUNdpBjz5BQPOBYelV5PcEn4H2EcWPLWZiBZsauruzeDSGf2PA");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(4);
        state.setStateCodeChar2("5w4eYklwPFY5VWZhDbt769UsSIinmjpW");
        state.setStateCapitalLongitude(1);
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("GVoidaJV57bnpA9HTo4TXVc6mIY0DYqW");
        city.setCityLongitude(2);
        city.setCityFlag("LkCrDrb0xskE3Z4QrCvA9R6Qu5h03RXFCeQCU5JWVLjtMny8qT");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityCode(3);
        city.setCityLatitude(11);
        city.setCityDescription("7MZNxD8qyg2G0v7IkNPm70yHqmX8q5o8onGRqrC9vLpcggInm3");
        city.setCityName("Sy4NRDqP1aHeh1MpNhXOGz0h2nZKp9on2d0A0vS2TLDDHo2Sfz");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
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
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("XVN1gHeHHIG1C5dmticMUeRnJOKtzCz8");
            city.setVersionId(1);
            city.setCityLongitude(9);
            city.setCityFlag("6iHtKqiBKRbRBO8FDjc5RP8wzkROPxkqMwTUyihvbXsL3tIIMb");
            city.setCityCode(2);
            city.setCityLatitude(11);
            city.setCityDescription("8VeRSvv7V8v14lc7xgkO5nPw9H9o2to5dHt7QMKtIDncyR8T01");
            city.setCityName("5Dkxm06lUS5uRTRZcIHXhKJMks4MjWUOkx931JzgmCabmQnmqT");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "OQAJvWbVNl1wd6qflfo2JgumWTGAfsAVtZRyivKKlqQE097YABLukQRZkKKNqvkQlvTX10yADTzq92bsG8JNphPcTIfQ4nf2vWaXyY57zIlf7Yh4QccLtXIBMhI6ZujAgoCcVCvObT6bu4AhRnz8iLjzCJhI9dwyIHGIetHbWqKOy9orWRbyXgLPBltTGSS2nN90QgwPoSc37O7FDhCulLECYVSKEUz8kvJuTckfRxG4yVTgHzOskzgp9HcYTUzve"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "lGcOjc8YtbNbvKPWnbUm1IoFp8MGk4e2t"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 6));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "Z5xWhWW798fJ6oTv4tj4lV0rSNr3AQDn0Ts6lsfCgAsukg8VOH7qjXf6P7rd5PnLdei5pxzYpJqbPMWOBojMJN1oN0OQookuDBvAbBj6tXNtT2MWRZWbm8hGyogw50LsZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "wQFjxTGadyYe1TQsIee0Lvl0gkhlrjV5l9LJoPUIxx9uIHwvR2EyEvkLnyCLa1LD9wCXJELQdfJtvIknPRls1Y1cK7PZyLWI3vUfGm56qVuTfCYASHWed3R41Iv7dU4IC"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 15));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
