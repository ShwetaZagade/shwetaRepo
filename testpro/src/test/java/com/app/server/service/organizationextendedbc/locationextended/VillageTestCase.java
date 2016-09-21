package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.VillageRepository;
import com.app.shared.organizationextendedbc.locationextended.Village;
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
import com.app.shared.organizationextendedbc.locationextended.Region;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organizationextendedbc.locationextended.Taluka;
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;
import com.app.shared.organizationextendedbc.locationextended.District;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class VillageTestCase extends EntityTestCriteria {

    /**
     * VillageRepository Variable
     */
    @Autowired
    private VillageRepository<Village> villageRepository;

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

    private Village createVillage(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("qw34SMz7OL2Gvc0wzH8fd67IUEJw5HYL0HQJuKy9p9NKa2Fuvt");
        Country country = new Country();
        country.setCapitalLatitude(4);
        country.setCountryCode1("uMa");
        country.setIsoNumeric(583);
        country.setCurrencySymbol("T4j1oklhxjj4fuAV02KFkCZNoTU1IdPY");
        country.setCountryName("IYlwyw8ZFXQsBbHgDSz1GtQDPGojtJ21HYsjMcztdh1I9QfUnV");
        country.setCurrencyCode("gxV");
        country.setCountryCode2("4lN");
        country.setCapitalLongitude(4);
        country.setCapital("olFfb6bt30bNIxM7sN4DtvqHQmXnuqOG");
        country.setCurrencyName("TGEXrKSjrHpZFSZWtN6eCB6DmDLUDSQSHFrGrMvh1yOK1DPCBm");
        country.setCountryFlag("MsS61uzp3XTvNumpFefZWcwN9ToAkURmWpzAt0Luuv02zaDX1a");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("MZVMD6qQakhEMbD4qoVkXfacFcdRNMbF9jhD0xogag8CfBbqWc");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("ixec7wryuzC5jSX7cn93TskyyHe0HQgBsC19ZGMMpyn7EBylXt");
        state.setStateCapitalLatitude(8);
        state.setStateCodeChar3("wZRffcC2UUKwto0tFDZ4XvBmQSwDGBS4");
        state.setStateCode(1);
        state.setStateCodeChar2("a2EP0SSZ2CXTokpCdBhwnEDx1wNBKzrM");
        state.setStateCapital("94P0ldvSlqYUNohAFnEjCpnD4XHDOOSDtr60ACEzlKuKHtC9Sg");
        state.setStateFlag("bqtr5vYV1LsyDB2fiznGhaoq1lZu1PGITHbizy7eDXn6C49Lpn");
        state.setStateCapitalLongitude(9);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Region region = new Region();
        region.setRegionLatitude(4);
        region.setRegionFlag("fxAIM215I2g1dzxWJPtqwcpnw3uCu3DnHlifZi35uU30Hh78gA");
        region.setRegionLongitude(11);
        region.setRegionLatitude(2);
        region.setRegionFlag("EFXzr3cQlkcEo7G3PN0UVK8FESQ1m2uYmxaaF8USx0zYkIscn1");
        region.setRegionLongitude(8);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(3);
        region.setRegionDescription("fsZK5N7JFRhyZAD3PcKVcUDQbyOHcdGM6cvSzXCuD43DMZZae7");
        region.setRegionCodeChar2("alWXM1v2aCHgG8g7fHNvzBuz3RCmEKWq");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionName("2crqKKbLpbDyNHxL8jkKxc4U1GI4R7Brp4vQKfZqWBznODQtSs");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        taluka.setTalukaLatitude(1);
        taluka.setTalukaFlag("e0VQxe2S8ZfExODzDvCYNQGi8LuYsxXF6Ry2KSf4p2m4Jljasm");
        taluka.setTalukaName("E9T0nwad1zXgOypI7lakg1bix68ePlaXA4uW0VqfeZAZZ1fM8q");
        taluka.setTalukaLongitude(1);
        District district = new District();
        district.setDistrictDescription("5UBmSx0ahh4RAWO5Cpfr5u8u5hbusyIutSvTso2zmBJQlXp9no");
        district.setDistrictDescription("GcXuT660jVdBGrQFb71EmpRHml92ER0kfuywxvlGLdvlGiK9Wh");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("BC2et5FApJSHzgaC8kYvRjsrbqDSaaUa0AmKmRWSoUGNlSFpxQ");
        district.setDistrictLatitude(11);
        district.setDistrictLongitude(1);
        district.setCode2("we2syIzFtsO9NmD8WktmFsvW6pOhlYgE");
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictFlag("BhLUbumjUjLJPAddeg86W8nhc5YhrwzbQ3AzmaAiL8x4Apc1CK");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        taluka.setTalukaLatitude(6);
        taluka.setTalukaFlag("eBQVTjkrrh3iFOdWB0s1XmhOqc3fILVU5eXlHCbcoAYYyCnsnk");
        taluka.setTalukaName("gbz8GFFFqcDXYGzlcMn9IGGMbR24GImvZNk3xosWzgrCNTwNpX");
        taluka.setTalukaLongitude(11);
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaDescription("cM8ihlDZgoUnG38xciNulIwdMHUeibZlBLeYBH18DtNCYWa23y");
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaCode("1cb3FpX81a9Xj4kJG6kIYVy2cIiZsQDj");
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Taluka TalukaTest = new Taluka();
        if (isSave) {
            TalukaTest = talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        }
        Village village = new Village();
        village.setVillageCode("z5cqTyrG2utPc8czAy31NDSs3BcFJADj");
        village.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageLatitude("Tosop7ueBWe");
        village.setTalukaaId((java.lang.String) TalukaTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageName("tT94GEHJNM80PZzLjS35KoJA0z2N2aALGHwGleYeuDLxEIsqoQ");
        village.setVillageLongtitude(1);
        village.setVillageDescription("JOD83FfKj67vqE4rpHbTzpQUUbypBFTJNjVLMJBK29ARYG9RHr");
        village.setVillageFlag("M9jIX9lTXXgUqUMrBiiScZ1ATGrGO740f1ySh5vb4QiPLmJbmk");
        village.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        village.setEntityValidator(entityValidator);
        return village;
    }

    @Test
    public void test1Save() {
        try {
            Village village = createVillage(true);
            village.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            village.isValid();
            villageRepository.save(village);
            map.put("VillagePrimaryKey", village._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("VillagePrimaryKey"));
            Village village = villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
            village.setVillageCode("7pIbIz5dFtAFv3yDSrcLnqsPbcU9Qg7B");
            village.setVersionId(1);
            village.setVillageLatitude("FkB0lW2eYeV");
            village.setVillageName("fkGChyBidFz2quJnQ9jrb6KDbrodfXVng9DLutlnwvRlPJNo6h");
            village.setVillageLongtitude(10);
            village.setVillageDescription("wmdpBe3lBc0vgcSipWEdTdiE87X4NXfJfwRYV3iUJIla7WgvLc");
            village.setVillageFlag("6qB5hBrwt57IkEVinhGEmNVafIz6naUOn5tfWNt7XYuFZMbWhS");
            village.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            villageRepository.update(village);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Village> listofstateId = villageRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Village> listofregionId = villageRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
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
            Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytalukaaId() {
        try {
            java.util.List<Village> listoftalukaaId = villageRepository.findByTalukaaId((java.lang.String) map.get("TalukaPrimaryKey"));
            if (listoftalukaaId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Village> listofdistrictId = villageRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
            if (listofdistrictId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Village> listofcountryId = villageRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.delete((java.lang.String) map.get("VillagePrimaryKey")); /* Deleting refrenced data */
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateVillage(EntityTestCriteria contraints, Village village) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            village.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            village.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            village.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            villageRepository.save(village);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "villageName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "villageName", "yKhsC5Ch5VeYxSD266GwsRI8P0fPOQVKMM0hBxceufQqEGCl0JHeFjOo1gewFdD4XwK0NuB0znJBALXHxuuohpiTElClsfrsVdpG8rhXqb43FUsTYYKbC4CjAwGRtTaXccls6xfQnik2o7LhHmSvLrhhHRKvF9CBcdVjs6D3yC0aWKkhhanmnFAEEFzHWu7opOlJ3gZqxcdG7gj7oWWMQ8DdhwdsYKiXcJIuq6v8KgvHESUGgQNFPF6DoaMCPkgd3"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "villageDescription", "kRDMVLTFYriUHIjdqvp1WwFyvrUsRz67Pk9V0qBtAIcyZx4p6RPPQQalssoeaKAcM0xGLZZWgBBWRTBlyMkz2mx1AMBUyzMADLhs3sq5sXrfJ6BYrHCuZDfzJFgMeTScsztFwvImUOyKocbmkONDkb4fv05ZHCuLW2aJE4xViptHQiFgrkNstdhLxiHmL3Nc4eXZkU6SUZR3moHxfcKYTLcGi985E7S4wSmX8pwz4zn1Ju1Alz2RmktAY0Or7GlkH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "villageFlag", "UThq6nBCYtfcm4RL4GcpBDZIIgpao69N37v9fNbmbzuMQ7w8edWB2PTZ6nq14rgqG"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "villageCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "villageCode", "tRE6cR9jz1gmihCvt0ZDLxlJ8q71pUhYI"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "villageLatitude", "poehHTQdEJTD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "villageLongtitude", 16));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Village village = createVillage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = village.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 2:
                        village.setVillageName(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 3:
                        village.setVillageDescription(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 4:
                        village.setVillageFlag(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 6:
                        village.setVillageCode(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 7:
                        village.setVillageLatitude(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 8:
                        village.setVillageLongtitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateVillage(contraints, village);
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
