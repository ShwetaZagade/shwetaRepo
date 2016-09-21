package com.app.server.service.airlineboundedcontext.reservedomain;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.airlineboundedcontext.reservedomain.PassangerRepository;
import com.app.shared.airlineboundedcontext.reservedomain.Passanger;
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
import com.app.shared.organizationextendedbc.locationextended.AddressExtended;
import com.app.server.repository.organizationextendedbc.locationextended.AddressExtendedRepository;
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
public class PassangerTestCase extends EntityTestCriteria {

    /**
     * PassangerRepository Variable
     */
    @Autowired
    private PassangerRepository<Passanger> passangerRepository;

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

    private Passanger createPassanger(Boolean isSave) throws Exception {
        AddressExtended addressextended = new AddressExtended();
        addressextended.setTalukaName("7TEucxrXsV42Ca9FmOnugP3TRsoBbTJDKLhWwr83rMI1vtyciT");
        District district = new District();
        district.setDistrictDescription("1elx89Vim6FURuCJUNURbAK3mLQG2BNoWgDpfT4vVQeKnzbjNk");
        Region region = new Region();
        region.setRegionLatitude(2);
        region.setRegionFlag("1zYvHOm95eH3A1zquxOYTnPhzARYWVbj4KvcDJ0sIFUVjxXQ8d");
        region.setRegionLongitude(3);
        State state = new State();
        state.setStateName("irOD1VsFvRLBQrniBXGeE6Aet4CDI4wAg5RuB293ch0I9HAEGI");
        Country country = new Country();
        country.setCapitalLatitude(6);
        country.setCountryCode1("A1n");
        country.setIsoNumeric(417);
        country.setCurrencySymbol("GRWkaUGQxSaiGf3WP4GvhjOHNrYbaTEv");
        country.setCountryName("JMZWTSfbkZ3OC9vxuXI0i6rwtKJ8fkdQC3ffgQ2xkzF95WvZDk");
        country.setCurrencyCode("a6M");
        country.setCountryCode2("hug");
        country.setCapitalLongitude(9);
        country.setCapital("aNw5RnHkG2stp08eNrVf0YRWRrdeWqqV");
        country.setCurrencyName("XrfW9WMIDdLxJaJII4akodBQShO3afLpEwu66xMgi5OuK0TLyz");
        country.setCountryFlag("Wyl4zHJHBZmyxhRLp2PvKXDKQ7v69e3pBXMigRo2arCVL5tkCU");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("Nb6Zc8rxtewO91nypHiauHwiHYvaLN4nfm3QDSE5vRLHacfevb");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("Nm3ftD07I9sDYpQnp3Z7e1z6MrQxGtjlVSNsgHoOE9P6d0w2OC");
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar3("mhpi3e9y4Il4pE0dETWHts3z1OEehxOp");
        state.setStateCode(2);
        state.setStateCodeChar2("18RssSvGH08vk5vLlgjngM5ILPSMGIDv");
        state.setStateCapital("XO0osjeNKnOZ5PndYVJ6Fj7TfxntLB907dutdVpbhdXYpboWd8");
        state.setStateFlag("ioMuNPrrsjclphnETWQjLk4ddvYkjCPdMb0yHwzRB3VZin3HB7");
        state.setStateCapitalLongitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionLatitude(3);
        region.setRegionFlag("E9Gb9043TPgH6qB8ZEM4jCstZHpg8OgL3JabaAap5aunEWRy2l");
        region.setRegionLongitude(2);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(3);
        region.setRegionDescription("ReRlkHOdRL4YzljPIXcTTPIpIqH6vMcl3DV2M14gIGGktL306a");
        region.setRegionCodeChar2("1CrcNzJs3MsVDnUqi38KRXDnx99Cj98e");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionName("KF18cxLKNuHT60nWnsI4Rmc7CbDs9UbBJrQTJF5VAmgEinx6jJ");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        district.setDistrictDescription("s2KjS6uvRory98PBP5aFvSt68kRSXB2yf97a0CoNKETCrbILsE");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("hO6cZfxjsFUoOQnDIqwCuRKKZw96SY6JJmOLkwSU9FuwaQSB3b");
        district.setDistrictLatitude(5);
        district.setDistrictLongitude(9);
        district.setCode2("a0c966Roi859wWRq6baTkDEsI9zdOyuy");
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictFlag("bkZk4ipcxmqDkum6Zvy2Df4ks9fWMgIeYrqSDYfA3zZDLRVglb");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("b9vDLhL9wVzhToyVEkfHvff49rSgijV53f4OWNSxaZMFybviel");
        addresstype.setAddressType("NkWpAGp4765MLZkSbhzEM0b77j4605AqEcR619nNp4xcmyePKM");
        addresstype.setAddressTypeIcon("k6RRxXDp41OGKrmTaySwMmkRUwOVmjNvdjrRu5aG8Ukq5XXxtW");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(2);
        city.setCityDescription("cIRNmkha0KSXZOJI3uhISt5ocU61ppkiWLRpCEXjlXxTwPkI8X");
        city.setCityName("3rOOoGEGONZ4o01lnbCgq4inZeuzDw1C8SvzDN7bz3pBns0e7Z");
        city.setCityCode(2);
        city.setCityLatitude(11);
        city.setCityDescription("od1vA7LM2HoFLNkkvhv0I85T2YkIji3XMBfbM5jhqXjhHPWgfz");
        city.setCityName("0DQScuYMrz9p5ewbxdwHmi9vTkf6xewLOj9xdYIN1al6UIp2dj");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("gtkLzhhfKHV6AOUJA9AL076wyXoPv23Y");
        city.setCityLongitude(5);
        city.setCityFlag("rjJiDyQPetam4rr9Pd0tN38PfMeGxOpDGPA7ZA4LOPwt368GjK");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("asToGvh76qjeK0MHuavQvuwEEUgSeHYy8RdyG8B9cgK7E0lVem");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("zg7UNETwmRP");
        address.setZipcode("1tGecB");
        address.setLongitude("9cmlmUfuQhLzhSKJPwKv6DJWQI4zcysl2ApJCDs3eRTYvxIDuK");
        address.setLatitude("ZEg4IfK8QS2oNBeHuYbYQRtdoZsm5i6NbXGxJXR6iYTq7nm1fe");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("IgLT3nB0ItNq97TpQUkbZbn0zR6DMqxIwZzbux1tfJ5h9Iae8L");
        address.setAddress3("t9QTNJQ8HjiOQ463G5nilpY1t8vOe6G1XfrvZ1eSy8rHgrtdp0");
        Address AddressTest = new Address();
        if (isSave) {
            AddressTest = addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        Village village = new Village();
        village.setVillageCode("8yLQLMoJ155rn2GHnOCpuXDFdD9818MG");
        Taluka taluka = new Taluka();
        taluka.setTalukaLatitude(4);
        taluka.setTalukaFlag("A9HOV7zebuldTHBJ9WxWJyk7m2faGQmM3JG7Z0w63j4tIOqtG4");
        taluka.setTalukaName("MdkGLzOV7hia5cBGhD7pQc70KkKReolyclOJVTqAHk5ho3Kxaa");
        taluka.setTalukaLongitude(5);
        taluka.setTalukaLatitude(4);
        taluka.setTalukaFlag("zUZ4ZwRgou9Zb5eMCC9jhjYllwzT45U0NTb2QUdFGKSVbdDSDL");
        taluka.setTalukaName("p7q2BGmTrbxbEfZIotT0vhEepF2BMXCIdAvYJnNa9wzMNDUjTd");
        taluka.setTalukaLongitude(9);
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaDescription("dM8DLl9tImJtYwlVcGuFsK9WQkaYIvqZO1bkAvdwZiR6BPkOS5");
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaCode("PIM5mTnfIGNVG04X3kDGJ7dAQ1WgY3Qa");
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Taluka TalukaTest = new Taluka();
        if (isSave) {
            TalukaTest = talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        }
        village.setVillageCode("dM27URgpDBLdyi9jRSQbQ0ykCoHVLhDg");
        village.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageLatitude("vg8jIHfoxkB");
        village.setTalukaaId((java.lang.String) TalukaTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageName("Bn5vVOAEeIkVz5u8ekY6KafliGehSIuNx9hbyJwuBslDKxAS0J");
        village.setVillageLongtitude(7);
        village.setVillageDescription("okX23nubWQTUufnd1TjrSKkQGrVYFiCDvp0hCTkG3xlGIlElj1");
        village.setVillageFlag("mtantXlLqrMp8pt0tkPkuDwCg2yLocYDvxl5pGYgbDXsUiRLPN");
        village.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        Village VillageTest = new Village();
        if (isSave) {
            VillageTest = villageRepository.save(village);
            map.put("VillagePrimaryKey", village._getPrimarykey());
        }
        addressextended.setTalukaName("Lp623y4FpzT4l5R2ycJoLujxRFChSurpEB1JuHIJxPprJP8tim");
        addressextended.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setVillageName("HpRKZ3LVNsiellLULgVa5NXuHqm3rft1rFW1tKavM5xcV1fAhz");
        addressextended.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setAddressId((java.lang.String) AddressTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setVillageId((java.lang.String) VillageTest._getPrimarykey()); /* ******Adding refrenced table data */
        addressextended.setTalukaId((java.lang.String) TalukaTest._getPrimarykey()); /* ******Adding refrenced table data */
        AddressExtended AddressExtendedTest = new AddressExtended();
        if (isSave) {
            AddressExtendedTest = addressextendedRepository.save(addressextended);
            map.put("AddressExtendedPrimaryKey", addressextended._getPrimarykey());
        }
        Passanger passanger = new Passanger();
        passanger.setPassportNo("mvxcSgcM04QGUAb9KosCFXiXyBsQW3DnuMS6EBT6x6J5gSSebT");
        passanger.setDob(new java.sql.Date(123456789));
        passanger.setPassangerAddr((java.lang.String) AddressExtendedTest._getPrimarykey());
        passanger.setPassangerName("ySB5i6c8qmoGVu3s61W9TQ4bBjNcy6dEvUq4gWSEDhfnRGLaBg");
        passanger.setEntityValidator(entityValidator);
        return passanger;
    }

    @Test
    public void test1Save() {
        try {
            Passanger passanger = createPassanger(true);
            passanger.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passanger.isValid();
            passangerRepository.save(passanger);
            map.put("PassangerPrimaryKey", passanger._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressExtendedRepository<AddressExtended> addressextendedRepository;

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
            Assert.assertNotNull(map.get("PassangerPrimaryKey"));
            Passanger passanger = passangerRepository.findById((java.lang.String) map.get("PassangerPrimaryKey"));
            passanger.setPassportNo("hza1fowzJVyDti7n4gNwFLuy73b5FHY4qysymZsv1sl22PJGqV");
            passanger.setDob(new java.sql.Date(123456789));
            passanger.setVersionId(1);
            passanger.setPassangerName("TYoQ756VucL7IMqVU0xQKlbYCwbQB7eTfGwbqCzlmJPksn8s5w");
            passanger.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passangerRepository.update(passanger);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PassangerPrimaryKey"));
            passangerRepository.findById((java.lang.String) map.get("PassangerPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBypassangerAddr() {
        try {
            java.util.List<Passanger> listofpassangerAddr = passangerRepository.findByPassangerAddr((java.lang.String) map.get("AddressExtendedPrimaryKey"));
            if (listofpassangerAddr.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PassangerPrimaryKey"));
            passangerRepository.delete((java.lang.String) map.get("PassangerPrimaryKey")); /* Deleting refrenced data */
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

    private void validatePassanger(EntityTestCriteria contraints, Passanger passanger) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passanger.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passanger.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passanger.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passangerRepository.save(passanger);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "passangerName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "passangerName", "osvERHHxi9QleIfyrG8UkmpRifID78smBA4DUsYXWXsn7j71dUi3p6DAqIi19qeF24zaEhsF3tmFfe8hChR6H5mk161k0aTkda0KJtiN8Tta6p9zFMZlqGwDQ84QTaMdVhHqOUuyfChVFtgDosQJvHv7Pjn8xVpyUpwU6FsGNRvUw1TNihhvMTQZZovdpW8hnTlDxtDncnRSKFLqdinbsgLlgxt7yzS29W1aJm0wQz5mI6e2uYOyfkYQUXzl0z5Sk"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "passportNo", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "passportNo", "a0khQKsPjeo9iERJIt9IVMqRHnwdG2rHSf3fiSUDiZNJx6ZGFYS02c1Jw2oql4aFA3CFwL9Wk92UUVI3w76Gje4cbRg5011e2cOCJlWsXIKhTz1qIGufRqxyqHlRkNYU0bxMeoix7qZpPaLzvftEhsUDigjUUO6RQAkyMo0boCm5bsTokcqikxTmQubdmTZJrVPOWvnMVxAyiaNTQarecKRJ16WgMmToHsvROzjjuUrwvxJ0awqPK7Cpc8lw1N0sP"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Passanger passanger = createPassanger(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passanger.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passanger, null);
                        validatePassanger(contraints, passanger);
                        failureCount++;
                        break;
                    case 2:
                        passanger.setPassangerName(contraints.getNegativeValue().toString());
                        validatePassanger(contraints, passanger);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passanger, null);
                        validatePassanger(contraints, passanger);
                        failureCount++;
                        break;
                    case 4:
                        passanger.setPassportNo(contraints.getNegativeValue().toString());
                        validatePassanger(contraints, passanger);
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
