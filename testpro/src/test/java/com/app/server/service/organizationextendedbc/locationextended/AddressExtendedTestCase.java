package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.AddressExtendedRepository;
import com.app.shared.organizationextendedbc.locationextended.AddressExtended;
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
import com.app.shared.organizationextendedbc.locationextended.District;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;
import com.app.shared.organizationextendedbc.locationextended.Region;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organizationextendedbc.locationextended.Village;
import com.app.server.repository.organizationextendedbc.locationextended.VillageRepository;
import com.app.shared.organizationextendedbc.locationextended.Taluka;
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressExtendedTestCase extends EntityTestCriteria {

    /**
     * AddressExtendedRepository Variable
     */
    @Autowired
    private AddressExtendedRepository<AddressExtended> addressextendedRepository;

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

    private AddressExtended createAddressExtended(Boolean isSave) throws Exception {
        District district = new District();
        district.setDistrictDescription("bjVLXIwrtUXqPdOy74oOuBpW0jwktJpryt8Klh0KS0GfVuq3Y5");
        Region region = new Region();
        region.setRegionLatitude(9);
        region.setRegionFlag("qEnDPVufOEj73t7rKkDzHkfn5ySEIiRVrIf33tK5ycWgFXn5JY");
        region.setRegionLongitude(6);
        State state = new State();
        state.setStateName("YGaYZLHpktqqKz0BBWpiRogC0psahWyNeoddPxCkyFE1yddYF9");
        Country country = new Country();
        country.setCapitalLatitude(9);
        country.setCountryCode1("RKA");
        country.setIsoNumeric(360);
        country.setCurrencySymbol("vuDNHuUiKtlghVOqAFcksLLc1isvlsaP");
        country.setCountryName("86ChEdIpiUMPoikckbe43nVCcO9vCanBInSanwv1xbb75WML1Y");
        country.setCurrencyCode("gvh");
        country.setCountryCode2("3rL");
        country.setCapitalLongitude(8);
        country.setCapital("FRDByvWSlDuundtp0QHayzxozK4dBwWP");
        country.setCurrencyName("kDc0R3425E6EPNGX8rUI1lQBSP9buQja4j4PmUTD4dyleuonHV");
        country.setCountryFlag("d8P9NEAy6IXjACX88JSxD3s99FJBKrqCVcpJ9tBJy3hGNaA4No");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("qZyMIXuzE6ipHYQ7qvw5Ct28lFakNLqGEdqT6x1Qjjp1MukFBK");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("YmNizGSxSCnE8t428iSNHZhfOfJkHQED3kzN7ulyEM7hpvFpMi");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar3("KugD3yGB7eS4bRUWksqr57D81RrneQQl");
        state.setStateCode(1);
        state.setStateCodeChar2("goelhxLVjvZuAg4XWRGAxS42jAgGB2OJ");
        state.setStateCapital("EEPWO5X7vhO5ypwHhbPuBSrSBw4yWjKJxS3Ix2zZyMzKGFv98d");
        state.setStateFlag("eNdeXha1dUTcTq0QpJchoc2qpVXoQnqyxXeu9BScPfWcZ8CG3w");
        state.setStateCapitalLongitude(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionLatitude(2);
        region.setRegionFlag("FFXq12fFCkLajfbBMiAeSDA1OaA2mXe8OWePoXehYZI1kaUc94");
        region.setRegionLongitude(11);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(1);
        region.setRegionDescription("1hvQ3eoFFjixVAxXVBEekLTZmnwPYpokdo232KnzgZfjZwutEF");
        region.setRegionCodeChar2("rYWuWnQYvZ77IPzLulZwCsjsSL7RnniD");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionName("tbkIc3XcDSG6SknBt0yfx927xUZ3cb6g3JtFUMDQN3OxgqXkKY");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        district.setDistrictDescription("qB95jBYf9DLdEKAYbJEz9gzb7dZLn8vInCtbzgjedX8SdOeAC0");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("7fbPGJL8qBbqQVumIFYmKWMCK0s2mnZAu1qKKnoccYCSahrPLl");
        district.setDistrictLatitude(9);
        district.setDistrictLongitude(4);
        district.setCode2("C5WR0Ft2CFR5rSzf7PIrpVrToCdq30g5");
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictFlag("XUebpEFHSAOeWKftfnhEjuYisXCvFl2J9JbNg9KhEPwLQTmOYZ");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("Sm44AtguTnkKPeTFVgxK5hSOAV7hrAzjG23yoo8ns7kTSuOjlw");
        addresstype.setAddressType("qS4K0IwU4jZ4bFnf9PGNtHGy22CsDwrxPVBoPQyA4YZYyKqzif");
        addresstype.setAddressTypeIcon("mMSdIUWB7ieICqxnFo7M1bsrnHbCJ1kJbEZAfLKw72ejHFMUjd");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(8);
        city.setCityDescription("AdilmfrKCuZCK9H0vbDY2OpY178krGpszGI4EJhWtAHZICVOCH");
        city.setCityName("FNSch43nw5a6dIurmxOfyBgik8QjhO5a4WIulLLbdsoZwiCkG4");
        city.setCityCode(3);
        city.setCityLatitude(5);
        city.setCityDescription("pGpQs2SI8b34NBxTc3Vtk8VufszTDUyT3gufxO4hLBAqD8l8Ro");
        city.setCityName("F1uUgQXIkrEanb7qV0SEqjBfZe3gY8DWKpnbyhiR88PsHuJbby");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("BMC6ECcWbrW1nQUVsV4PyDn792AEHc7Y");
        city.setCityLongitude(10);
        city.setCityFlag("zMZC6VPsuX8VoH6oJ4QHdtJ7Vmtd5AOuOmLzTvNI2AuWcV5bKb");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("0mvqm1B3dxOVK2RuVyR6GTPwD7rrMexn1CfrAG3nzJkvbNPJD1");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("CGBBAtov95s");
        address.setZipcode("GoRvTE");
        address.setLongitude("1BbjzrB8Fmf10mGDQoawKZNYTc1ZOftetKvXjwwNkzO16N1u36");
        address.setLatitude("VNEP3So2zWex8TVuUuQ9B27bcdaYW3jvafSDPmNwxKWgWvRoBV");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("D9WJfUgLzhZqQN4YPm768rUjKeTji6bOLAlamXCXaNbOLsDmx9");
        address.setAddress3("SWPTIryPyRLnf1IMt4Ebqf7SOrUVZ2VVf9O7yrbOd9xzxlpGZx");
        Address AddressTest = new Address();
        if (isSave) {
            AddressTest = addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        Village village = new Village();
        village.setVillageCode("a6V54T8tIEjgWBo0Dfii89y6wLkJNKBa");
        Taluka taluka = new Taluka();
        taluka.setTalukaLatitude(11);
        taluka.setTalukaFlag("0cW3oHJBS6e4rlQBQY7Hq1WKnJ4xsfCE55o6IFvmZI3g0ctlkw");
        taluka.setTalukaName("EavRDJcgN7dfvjMF2VL0m7Vce5IfcMwrZyuYn5iRjB65aYcvOz");
        taluka.setTalukaLongitude(7);
        taluka.setTalukaLatitude(11);
        taluka.setTalukaFlag("2EagHDfTOzgspc8YULaBJ9DHJDdwu43PFWc1LDd0djSOpPyq8o");
        taluka.setTalukaName("fpyfmfXdk6zSbtc67XTAAWdj4TLkpY7dc1WtyfnGvsvmOjW0Lz");
        taluka.setTalukaLongitude(9);
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaDescription("lqlyi1a3jPH8TNFbSlulWlIpqJBYG5MXhsFIogGQZZVwfqQghS");
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaCode("C2dqd8XZLxOJcrbP7cVyUTCfB5HGn7zi");
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Taluka TalukaTest = new Taluka();
        if (isSave) {
            TalukaTest = talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        }
        village.setVillageCode("EkW8s9hfBSitQnEjYLWogMVyNvlv5yoO");
        village.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageLatitude("qNrdYb1KRfc");
        village.setTalukaaId((java.lang.String) TalukaTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageName("9wZdAkBVV2uELG4UiEErjaLc2hPFUuGffnFwPO02ICnZXKat9e");
        village.setVillageLongtitude(11);
        village.setVillageDescription("lbIISyi44w3huFbIerOuElOFdssRMB7gyk5koIIADf6r6arbJv");
        village.setVillageFlag("cFrCPk1PCXNZUUmM17MPyrsq5oh4u5WhVnHGi9bD3BLvaXAVWU");
        village.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Village VillageTest = new Village();
        if (isSave) {
            VillageTest = villageRepository.save(village);
            map.put("VillagePrimaryKey", village._getPrimarykey());
        }
        AddressExtended addressextended = new AddressExtended();
        addressextended.setTalukaName("LeozLZkfzdj89i01b9uzoigBPXJDBduZjPsf0QRD2dCgPEqHsc");
        addressextended.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setVillageName("luLxlN6iRnXK3uRFmaZRNyI5AfUemzHuBMKKbHSJyQ9LyaZAOu");
        addressextended.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setAddressId((java.lang.String) AddressTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setVillageId((java.lang.String) VillageTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setTalukaId((java.lang.String) TalukaTest._getPrimarykey());
        addressextended.setEntityValidator(entityValidator);
        return addressextended;
    }

    @Test
    public void test1Save() {
        try {
            AddressExtended addressextended = createAddressExtended(true);
            addressextended.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            addressextended.isValid();
            addressextendedRepository.save(addressextended);
            map.put("AddressExtendedPrimaryKey", addressextended._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private VillageRepository<Village> villageRepository;

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressExtendedPrimaryKey"));
            AddressExtended addressextended = addressextendedRepository.findById((java.lang.String) map.get("AddressExtendedPrimaryKey"));
            addressextended.setTalukaName("SzLorSC2Wj0ya4giawSdV8Zh9Ukbgyl1LxAxZlzNvxeaPwjM7c");
            addressextended.setVillageName("k1mESK8hyk0U0PNGMMSdMY6MBVPVfZaPAOuBZ1w60TkjUZ2UmH");
            addressextended.setVersionId(1);
            addressextended.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressextendedRepository.update(addressextended);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<AddressExtended> listofdistrictId = addressextendedRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
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
            java.util.List<AddressExtended> listofregionId = addressextendedRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressId() {
        try {
            java.util.List<AddressExtended> listofaddressId = addressextendedRepository.findByAddressId((java.lang.String) map.get("AddressPrimaryKey"));
            if (listofaddressId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByvillageId() {
        try {
            java.util.List<AddressExtended> listofvillageId = addressextendedRepository.findByVillageId((java.lang.String) map.get("VillagePrimaryKey"));
            if (listofvillageId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressExtendedPrimaryKey"));
            addressextendedRepository.findById((java.lang.String) map.get("AddressExtendedPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytalukaId() {
        try {
            java.util.List<AddressExtended> listoftalukaId = addressextendedRepository.findByTalukaId((java.lang.String) map.get("TalukaPrimaryKey"));
            if (listoftalukaId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressExtendedPrimaryKey"));
            addressextendedRepository.delete((java.lang.String) map.get("AddressExtendedPrimaryKey")); /* Deleting refrenced data */
            villageRepository.delete((java.lang.String) map.get("VillagePrimaryKey")); /* Deleting refrenced data */
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddressExtended(EntityTestCriteria contraints, AddressExtended addressextended) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            addressextended.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            addressextended.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            addressextended.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressextendedRepository.save(addressextended);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "villageName", "UtCdPdIC5nqA64HpMsCt1o3OTBNlsW54MT9JGBklwHwV61VUX6M8mO5T2ljsrjEKVfgDb82t0Mz0Qt9EDSR3UPLi1A089ZSJ92WyXP5CZrTlZNXRwO2Mf1MPLuqxtrW72"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "talukaName", "MnPUGLgmkrFY9KNaH7HNVriUNJOUiXSzNcahiPQJZwwLeFeqsOWxqenGPLQ3yCuaV6eHU9cqELFsbhI4ZtZY25bam03K6XwBVxuYpqgmXN3SFnA3Cwx7cZuePUHX1pcMo"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AddressExtended addressextended = createAddressExtended(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = addressextended.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        addressextended.setVillageName(contraints.getNegativeValue().toString());
                        validateAddressExtended(contraints, addressextended);
                        failureCount++;
                        break;
                    case 2:
                        addressextended.setTalukaName(contraints.getNegativeValue().toString());
                        validateAddressExtended(contraints, addressextended);
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
