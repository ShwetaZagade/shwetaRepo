package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;
import com.app.shared.organizationextendedbc.locationextended.Taluka;
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
import com.app.shared.organizationextendedbc.locationextended.District;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;
import com.app.shared.organizationextendedbc.locationextended.Region;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TalukaTestCase extends EntityTestCriteria {

    /**
     * TalukaRepository Variable
     */
    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

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

    private Taluka createTaluka(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("4ZVDucLesrZOT7bMi9VTSfpqfzzVNFbHNRALm5mULrje37QjMW");
        Country country = new Country();
        country.setCapitalLatitude(5);
        country.setCountryCode1("ATN");
        country.setIsoNumeric(113);
        country.setCurrencySymbol("h3oozIxLWhjxsIjtYBjXJ3b5WpUs2pho");
        country.setCountryName("ZUarCFe4G2B52Ezk58kKl9AuL2BsAepZfOxGxeqgWLIu3Ye5Bh");
        country.setCurrencyCode("4bp");
        country.setCountryCode2("mdP");
        country.setCapitalLongitude(10);
        country.setCapital("ahceLJfwt0iPKHzogxC26qyfWcKcIV5b");
        country.setCurrencyName("z0ctsa7sDHwlHvqrFF8FqDmcA96wXusCgYAKD0lXpcb0bk89iO");
        country.setCountryFlag("F6SlLQVxGRX1ysqcSMbdZOnK7IKc3ejxbGDSdA1Ewa7EaQwf01");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("3MghfXfllOFFCmlxZNTTgivKBl41jSSGjkMC6JtTrg4uyJPOMT");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("utELF7cXv2pACuwvyKEiosDUso7MOMBkVcVcxYNBjt9NZEqOwD");
        state.setStateCapitalLatitude(9);
        state.setStateCodeChar3("qWa0X1pbxm9YyaqzLpZE34RtllFuLMka");
        state.setStateCode(2);
        state.setStateCodeChar2("KtxUaXtgyYM011w2nQBInKPpkm8tJGUx");
        state.setStateCapital("d5nh8PUuvctqyQOiwlXFDlZJEtNyJ99Wty5V8JLiEfJvA0LUOC");
        state.setStateFlag("oOdLemPgApkb9LMGkPtdoVq6QyUPOj00WbdnHcE2WNqK5czg6o");
        state.setStateCapitalLongitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        District district = new District();
        district.setDistrictDescription("frZ1fM1Rggz0OSyWkLwD7k4bJHPqLxkaSNx6azu3csRFoIckf9");
        Region region = new Region();
        region.setRegionLatitude(6);
        region.setRegionFlag("tb6osgJNX8VCM0LL5VSb2lKlAktgLobhJ9ii9RLNtyXjfkUfFV");
        region.setRegionLongitude(11);
        region.setRegionLatitude(1);
        region.setRegionFlag("mWuWQNaMBv9BgTGIZDG7CWDBQkH14zYsl4BztnfnRniyNkcFsr");
        region.setRegionLongitude(3);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(2);
        region.setRegionDescription("gRoLtPFat5PLoXPmfOpCH2PnnPIaRCRyGfEx31FkYk5QHk8Inp");
        region.setRegionCodeChar2("JGCyxEgQrnDA5b0J3OPkWwOjOiBihTJy");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionName("DDoZ7NUJIw7LGQisTWHPamfBnZmQf0EylNJwnUjvo8h1QHJT06");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        district.setDistrictDescription("SgrxRu6C3HnLUwjQJ0C5cUzkJjDaI8ePNMNmRcUWLbHU8tWfFS");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("ytEn86vdhAwNLcBjccTJsmSvCAnqjwgg00T4dnZYU1mj3E9PCB");
        district.setDistrictLatitude(5);
        district.setDistrictLongitude(10);
        district.setCode2("oRwYI6gUlneSyyO7d4AHwzAinAhBn9Hc");
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictFlag("JQEt5yUsi6tPMDABnG4qFlImdRG82SglVQBTdiYibrIW4Z6l2u");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        taluka.setTalukaLatitude(11);
        taluka.setTalukaFlag("oBEo3Q5RcI2Lsi92d31tdxxTU33iEaFI2dcePpt4156zfocDXU");
        taluka.setTalukaName("6OABZzFgSctZdzOvO0GuNPLyRQj03SyOd7lzxB7GQfrlSkNNPC");
        taluka.setTalukaLongitude(4);
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaDescription("euULUF6SVogg4IQVxWTQYBbS3ldcUI8Yn1rZygNO3mWrace9Fi");
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaCode("rBMW0AcnnZIomHcZ2tAuJXlMeZ8yxPCR");
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        taluka.setEntityValidator(entityValidator);
        return taluka;
    }

    @Test
    public void test1Save() {
        try {
            Taluka taluka = createTaluka(true);
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            taluka.isValid();
            talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            Taluka taluka = talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
            taluka.setTalukaLatitude(8);
            taluka.setTalukaFlag("o3kKNqrfnFRbjGWOsXf6CCTT3JOmV4hLbSntw7SYV8CL4y3MWv");
            taluka.setVersionId(1);
            taluka.setTalukaName("Q6lo1I4jUqRiIHUAzlZoO4IzxB7ECgbt4lSyFRVt9YmBK2Y1fz");
            taluka.setTalukaLongitude(6);
            taluka.setTalukaDescription("G82slXgse0oQbUBFvgcGrrT6KGxYbFvxROgx4JLJDfkZ9iDkEx");
            taluka.setTalukaCode("ych8KpgcQZrpSWUjpdoxs8sTXk3o53NY");
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            talukaRepository.update(taluka);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Taluka> listofstateId = talukaRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Taluka> listofdistrictId = talukaRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
            if (listofdistrictId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Taluka> listofregionId = talukaRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Taluka> listofcountryId = talukaRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTaluka(EntityTestCriteria contraints, Taluka taluka) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            taluka.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            talukaRepository.save(taluka);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "talukaName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "talukaName", "Y2MXrr5Ruthn3M3C38NM1zJe9VuR26Uonuf88gvk1gub4l05vdcC10woiV2d8b2YdT90dyEAllvP3OorQ63N8qpesIM8BfymBhzvJeamhpxGof2lWTTXC4gSOnW74SG33jkgHv3D0fiYY6aV7U20cn1bNcnwb0RuMgZdzVmjwB58K7vYANoLmr8kitRDva9aqVSj38IvOegbS43K8caeiO3U5yV8Ck9W3fgjg0fsTb6ekYb1rlTH1ke7Agg3uu1uj"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "talukaCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "talukaCode", "cZzlbIJZz0AAqNUSQSIUnqCbaxEg4Slb0"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "talukaDescription", "MRQIcD843RRmMihL0ZfeFMuOfmQfEDFu14JXLD5IlaIw0DlWML2qgWBjJ9xLnktjtcLykWrHpNXwr6lHFcEfvX9EoPMgNk5AmpEWJiGYmmcylAJkrsGJvOMLM5PDqf0Wm"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "talukaFlag", "lO1YEoTtv8tIWnxMrA0ecBxg8o2KilbysYfJ3ooVL2FOBAa9DkgaPvjKCjt19zZaABdut5aT2SovSvErjLEHqMyvunt79EN4ix92JMiFkGBx1UHbL3oqm1aTmjW03ebVe"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "talukaLatitude", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "talukaLongitude", 15));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Taluka taluka = createTaluka(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = taluka.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 2:
                        taluka.setTalukaName(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 4:
                        taluka.setTalukaCode(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 5:
                        taluka.setTalukaDescription(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 6:
                        taluka.setTalukaFlag(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 7:
                        taluka.setTalukaLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 8:
                        taluka.setTalukaLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
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
