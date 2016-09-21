package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;
import com.app.shared.organizationextendedbc.locationextended.District;
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
import com.app.shared.organizationextendedbc.locationextended.Region;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class DistrictTestCase extends EntityTestCriteria {

    /**
     * DistrictRepository Variable
     */
    @Autowired
    private DistrictRepository<District> districtRepository;

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

    private District createDistrict(Boolean isSave) throws Exception {
        Region region = new Region();
        region.setRegionLatitude(11);
        region.setRegionFlag("BCxhPcCjJH5lS1nyYXX65MMZ34ywUn2iYbdHfODvp186smjbt8");
        region.setRegionLongitude(2);
        State state = new State();
        state.setStateName("frADznkCx1MVv0NRF6nB4EsHt1PX37QKdeNGoNkibI8ecdFZPy");
        Country country = new Country();
        country.setCapitalLatitude(5);
        country.setCountryCode1("SeO");
        country.setIsoNumeric(983);
        country.setCurrencySymbol("NzK3xObVbSYrXuUPmlm42QPfHG1FbYq2");
        country.setCountryName("ohiB4MFKDs0CuduYTYHu9OmbbeXjO4ZQgYD297XXaVdlmPxEk6");
        country.setCurrencyCode("WTh");
        country.setCountryCode2("dsz");
        country.setCapitalLongitude(11);
        country.setCapital("nV2Zu4evspT6n6pQFdEoKMSYBnr4O6GV");
        country.setCurrencyName("8BK2gGAmAaRkB0zvIu6Xf9gCkkbA3gSBcQNT8DTlnb3oaGOLem");
        country.setCountryFlag("rGCibGn2tFPrk0Jh4CFoFGqVPOynCzDl9djaKnABGuFlhk4UDv");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("lXhqR7WGsVzPaWeekYNKvJXNXn26hAffVKZXnAd93n8kykOWBM");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("oDzwESIGgURwS276vM6Oad2Yk1ZDdClepmDV7hwgfcr0AdINdO");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar3("JqwN7tu3wSWoMd9exPAIR0wETaRuL8MI");
        state.setStateCode(2);
        state.setStateCodeChar2("AC2hhxAR6kzMA7bA5N97RCwRccviXlbf");
        state.setStateCapital("Url3CIHRJYuum1E78BcNDgcQDpQppJcYwyflRWMaN5nSmTtfnm");
        state.setStateFlag("VLxoif0XM5QTqHSsXNpnsckpVTCGH8l55V1enpGByiktWBBw3L");
        state.setStateCapitalLongitude(6);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionLatitude(10);
        region.setRegionFlag("gYXeGEOUi9CffqociWWFuMXAGZCqGSEw5G6oIhdLA9hWMGAj2W");
        region.setRegionLongitude(9);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(1);
        region.setRegionDescription("hBBXydUDM35dUBuzoYAlYKQO3IVLgMslvT2bjB8e4iRHBlpQiq");
        region.setRegionCodeChar2("vDm9GZU2oLJL52Xq5l9aDA9AuThAKAzW");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionName("iZNj0Zjad9DL4xZg0CoHDDsVTditdbMsYUgX7a5Gx2ShXhwONn");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        District district = new District();
        district.setDistrictDescription("utsfwCbDtFl0EwSAGSYXa3cRTfRflWKDGJOyxmrKyPx1xxcOtP");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("4ThB3h43pVLGdHVTeyHy85ZLT1Dbhg62liXXGM6MrppuIdM9jv");
        district.setDistrictLatitude(3);
        district.setDistrictLongitude(1);
        district.setCode2("SBuZ2U0Oiu9DU1XlPBLGpe4wp5gH8ujT");
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictFlag("wlcyV2DAiYI5DZdtx92nEkbBM3VcVqEZrXnSzVGUdsM3mLpT7E");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        district.setEntityValidator(entityValidator);
        return district;
    }

    @Test
    public void test1Save() {
        try {
            District district = createDistrict(true);
            district.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            district.isValid();
            districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            District district = districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
            district.setDistrictDescription("EN7p5zV3pkQI3oQN2FFd4ShuTbYUNuR7xUaD5vOtEA9XoKOIER");
            district.setName("1GUvCAXjxCkdMgJykhhFKtnbk7riLOMCGatIB59LaPXtSLxnjn");
            district.setDistrictLatitude(5);
            district.setVersionId(1);
            district.setDistrictLongitude(4);
            district.setCode2("Vkx6rR8VN2oZpoJDvcg7iZli3NbqR5wg");
            district.setDistrictFlag("3Se9nkI3fj8c3FARjazFktCcCOO6UkxtAIt4lkClYxtz4OwMSB");
            district.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            districtRepository.update(district);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<District> listofregionId = districtRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<District> listofstateId = districtRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            java.util.List<District> listofcountryId = districtRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateDistrict(EntityTestCriteria contraints, District district) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            district.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            district.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            district.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            districtRepository.save(district);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "Name", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "name", "cHDljXzXEYey40YUqd8frpEdIVBv6Gw8JjDBKLFBXCJ1xt5D091O630h5KNn5k7jh8U1IzAj0QfEhvqy5OQ8affVNYGKeAIpC9R3Z4r4tnOTCjO0h1fLIMV8B6HxW39w86uhCmlEuzHFjz0YaLQELj3tcHxasGkfbHXcA4COg49fvsFHUNleXPinkZzc8hDIBjts2djOn8GD1QDGjCxhxAp9aCPcbaIMWcsGFTTFsjiiJLEM7pPaAwiGEt1SA2nno"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "code2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "code2", "FPr3EUJm1Nn87N8nLFLvj17IILH8cpsAm"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "districtDescription", "ydHB1NoqpSnNNzbByU7SL8egI46mrhmHZrQhjYf4nXHWB4nE3sMi25KQwfqjfVubi6pUv1Z9fohh2HoixVhG8xZbNcHkybvlX2NjdOR7ULaxKyPuq47rVvNGWcQ96yz15"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "districtFlag", "4YRkHtKwN5sg2EtUtDPHX0IxKWmIhkHhdvm77L15FeD5FSoNiIiYWwOoQ6y5vkajHLuQV3caHy47IhNgJ6MyQxnYV869XzW1WjeQb5V5JEC8CUoBQiI1RQW9AHaRex05N"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "districtLatitude", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "districtLongitude", 22));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                District district = createDistrict(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = district.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 2:
                        district.setName(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 4:
                        district.setCode2(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 5:
                        district.setDistrictDescription(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 6:
                        district.setDistrictFlag(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 7:
                        district.setDistrictLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 8:
                        district.setDistrictLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
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
