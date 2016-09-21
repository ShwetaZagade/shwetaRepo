package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organizationextendedbc.locationextended.Region;
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
public class RegionTestCase extends EntityTestCriteria {

    /**
     * RegionRepository Variable
     */
    @Autowired
    private RegionRepository<Region> regionRepository;

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

    private Region createRegion(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("0qGZMv8005qlK0rbX9WML2snzIxTLNhrBwjF2cIuhkAMmxI6io");
        Country country = new Country();
        country.setCapitalLatitude(2);
        country.setCountryCode1("Tma");
        country.setIsoNumeric(669);
        country.setCurrencySymbol("GewjxRfaHiRx3uAHkIz97kj8Hxq0Rf9k");
        country.setCountryName("UGvumHUOTi30095FaiQedlipm7cIBOHe0SzTjnfKSzIFECsAum");
        country.setCurrencyCode("SRo");
        country.setCountryCode2("F8c");
        country.setCapitalLongitude(11);
        country.setCapital("w3d1MVbnBy6FSN0N5leSWEJPcpvDEpaB");
        country.setCurrencyName("S9HfFnpwkojLnXPXJgVceUNQzw4OHCl6qL2FAmfumAZQ6mZLF2");
        country.setCountryFlag("Mjh9fgTUsyfgo4FdiVw1wVsi1ZNfF0eEdtBuC4FsvxfsRYX0l3");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("MCRIqdVFflf6iV5RqWr3sRdzh16j7NboFNNNvD8LKOrLSt79ry");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("vnBgbJAtDAEiduqbyLCurcTnUlyJtcsptboMA3XlU7kefS4EDF");
        state.setStateCapitalLatitude(6);
        state.setStateCodeChar3("dZY95rkTQi67J8H62D7UFffm7LuEl70P");
        state.setStateCode(1);
        state.setStateCodeChar2("Zlz9PWTDKTVL039Pw4Hevd6lFKtsJN8m");
        state.setStateCapital("C3JYRO1raOD0Ap0IOQAp4UtuXlgTTXQhmPx9sPpeAWBvQ2tiJV");
        state.setStateFlag("3J9aZaqks7W0GXE5IyPXDemSmK28EufSk6EHSthuXmo8mql9Xm");
        state.setStateCapitalLongitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Region region = new Region();
        region.setRegionLatitude(4);
        region.setRegionFlag("cExDYBfTJakjVbS2LamZm8qjnAuzxCjiopM0kjM8MRCEyMBf0z");
        region.setRegionLongitude(1);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(2);
        region.setRegionDescription("SwbTMhfPAZV5i0EB9u1ETgowZPyTpQFmJJqOYYj26UEeANe2Qh");
        region.setRegionCodeChar2("d40rYRwml7WNxeVmg1Bn8uLsvxNaNXcw");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        region.setRegionName("OiAcW64XyH1Jr8eF6FEt5ru8Ehj6ULQAPJd3YXlFsMT42Nbvig");
        region.setEntityValidator(entityValidator);
        return region;
    }

    @Test
    public void test1Save() {
        try {
            Region region = createRegion(true);
            region.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            region.isValid();
            regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
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
            Assert.assertNotNull(map.get("RegionPrimaryKey"));
            Region region = regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
            region.setRegionLatitude(6);
            region.setVersionId(1);
            region.setRegionFlag("NPOC9WDllf6WDfJGuFgkt228Bc6lsQ8doH8iCaocigGSItmPKX");
            region.setRegionLongitude(4);
            region.setRegionCode1(2);
            region.setRegionDescription("XxREscr0UWXFMDTmPQkcJNikZPkPclOdM0bsqzXsmoJQGKgJ6x");
            region.setRegionCodeChar2("EI1AHcg5rP13Dg3R5E20UkpRA3oVJcpz");
            region.setRegionName("k53SjX6u4zt15LedYm3kNgAF9finB4rz6pNSgj8Mb6h1CuE2j2");
            region.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            regionRepository.update(region);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Region> listofstateId = regionRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            java.util.List<Region> listofcountryId = regionRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRegion(EntityTestCriteria contraints, Region region) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            region.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            region.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            region.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            regionRepository.save(region);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "regionName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "regionName", "HUyauzlKcRzG2zkKcTcO7seuGge0kCwKd0tI17C6dcXoz3bvUUGzg4K9GCQspxl9bFnoHfehf4dB9V66ubNjRCqpS2fy9PAEg0nY3PFtzQ425az6en7fTAkjZZ4vhBMf4F16UtsxT07ZcS8bZF4qasGWDJNK1TYU3Zs88s6kCoftJKMNhQZy9dhRDpEU4zAHPwvYlcNti8IDjdHvmPZDeVDkvCu9498KajuiENpweaMS6yzEM3RauM4mDVM5o5Rge"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "regionCode1", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "regionCode1", 6));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "regionCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "regionCodeChar2", "E8SsBC91RwodkrZnWlU4upaHVXsRfwxT7"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "regionDescription", "viQG7EjVTpIF8zXVxeHWfG3PXLGSHxNhnbGK1OwJlJQ6kNMhYLuyuyW6OC2wGxuySF74uUiqWxQlt60Nb3g3USm6PVeF7fWHKwLc3xEgtZbIahZs0DKsXQjCLzrLJStEHBTSOpTYv57UQzS6IkxPCNdp8nisRaPps7LyzMU69Gn7zexOfKCOQBPt8SoHD1NzMYOY9ywXZ9ASVEXxzY3atHZbYVXazEzGdfvTOShF75XwDrgu21AXyNFjhekT2UYzt"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "regionFlag", "BFFVq58gfDP3QNiI3GT1HPan1f4jMCg1BaY8SWF79X3vEEQXRjeKaPklJT1CHieqaM0PRxwApK7G4C8YqPGiosX25TFjk00BwqDJbMDSk3TM66VgEjJhbcmqiV6Aao2W7"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "regionLatitude", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "regionLongitude", 21));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Region region = createRegion(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = region.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 2:
                        region.setRegionName(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 4:
                        region.setRegionCode1(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 6:
                        region.setRegionCodeChar2(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 7:
                        region.setRegionDescription(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 8:
                        region.setRegionFlag(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 9:
                        region.setRegionLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 10:
                        region.setRegionLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
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
