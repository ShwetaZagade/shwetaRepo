package com.app.server.service.airlineboundedcontext.reservedomain;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.airlineboundedcontext.reservedomain.FlightRepository;
import com.app.shared.airlineboundedcontext.reservedomain.Flight;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class FlightTestCase extends EntityTestCriteria {

    /**
     * FlightRepository Variable
     */
    @Autowired
    private FlightRepository<Flight> flightRepository;

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

    private Flight createFlight(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityLatitude(11);
        city.setCityDescription("9I0mxbwiDGO2REIiD5fDQbJeIeOLdqtYXuVLAMs7noxyR75rZd");
        city.setCityName("NTU759nOy1sLYDJiQFJTwOH2yX2KjXstdRxJJs9RGTzk4L3c21");
        city.setCityCode(2);
        State state = new State();
        state.setStateName("izXZA9Eij1gkWgVfPZBOPV9mt931N3ZuJBFZTyuKaCZXZT6s0t");
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryCode1("fTN");
        country.setIsoNumeric(672);
        country.setCurrencySymbol("Hs8Db6NsnXnBMYbDWBAcuKOOAnWiTt0m");
        country.setCountryName("Xkm8ujsnj17exSnv7JKEJRrrMO8Z0UHeOvx7C3kPBI0mohCxmw");
        country.setCurrencyCode("4AN");
        country.setCountryCode2("Kay");
        country.setCapitalLongitude(10);
        country.setCapital("FCz68QJpl9FwbymxGATmeRFEVt5qE0k5");
        country.setCurrencyName("z0VsIG5enfTOhiQe90Rc7JZfcPknoZtUgrgg6CPUmm8oaRy0IA");
        country.setCountryFlag("GNntxoRBZgTQsVQIH6dTu5cPJZreWpc59s56CzKoYv2fZmTE4I");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("SSclCZEH54YumTGgqeBelDDa2whRZr1bzrSC6fLE2gfEalplNZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("9l1JA2Sus9WweJgOdejX1pAG0HeD3KGpWk3ZVKUqBCBWoJDW15");
        state.setStateCapitalLatitude(3);
        state.setStateCodeChar3("qgC9pMLSL5RHR5poHQ2u9rxeD1F7MdU1");
        state.setStateCode(2);
        state.setStateCodeChar2("E71nvsl3yqa8L4tvFFI9h9g3hwCfN8Cu");
        state.setStateCapital("YfZIqkAdU1OcBz3VisjC4Z0wE1cEY4b8hIjLw2c6thorFnQcCN");
        state.setStateFlag("2MIyeRwMYLeCCNwLp0uvcomSaaLmz25fyRYMUOAxilJuRZfEeI");
        state.setStateCapitalLongitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(1);
        city.setCityDescription("VdWOaAbdh62rcBIxhEzkWjetqS0rByVwAsXjjczWShq9x7aZKn");
        city.setCityName("RM9RYP9ESQeADq9xGvF2jh5o9IuQDw8ltgNNZRCmQO0kCpIHlK");
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("0DuvQdZ5t2tl9N3X2Vu7q0SBHfw4X6CJ");
        city.setCityLongitude(6);
        city.setCityFlag("bueo8uGb70ILuf3aqGzGIthi0U1CJBoYefnOPfAITyye11VkkJ");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Flight flight = new Flight();
        flight.setDepartureDate(new java.sql.Date(123456789));
        flight.setFlightName("xUyrzmWecb8jGSIh5O6T4ae1vVQOPSN3GtzAvgguhcLdz0a0MJ");
        flight.setFareEconomy(2147483647);
        flight.setDepartureTime(new java.sql.Time(1474447892630l));
        flight.setBookFirst(2147483647);
        flight.setFlySource((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        flight.setFlyDestination((java.lang.String) CityTest._getPrimarykey());
        flight.setBookEcon(2147483647);
        flight.setTotalSeat(2147483647);
        flight.setFareFirst(2147483647);
        flight.setEntityValidator(entityValidator);
        return flight;
    }

    @Test
    public void test1Save() {
        try {
            Flight flight = createFlight(true);
            flight.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            flight.isValid();
            flightRepository.save(flight);
            map.put("FlightPrimaryKey", flight._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("FlightPrimaryKey"));
            Flight flight = flightRepository.findById((java.lang.String) map.get("FlightPrimaryKey"));
            flight.setDepartureDate(new java.sql.Date(123456789));
            flight.setFlightName("BxFraHooXSo2J7Sn3I1AqvBPEPuChQOk2Eu7MkIAkQXQ2pOaIR");
            flight.setFareEconomy(2147483647);
            flight.setVersionId(1);
            flight.setDepartureTime(new java.sql.Time(1474447892764l));
            flight.setBookFirst(2147483647);
            flight.setBookEcon(2147483647);
            flight.setTotalSeat(2147483647);
            flight.setFareFirst(2147483647);
            flight.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            flightRepository.update(flight);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByflySource() {
        try {
            java.util.List<Flight> listofflySource = flightRepository.findByFlySource((java.lang.String) map.get("CityPrimaryKey"));
            if (listofflySource.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByflyDestination() {
        try {
            java.util.List<Flight> listofflyDestination = flightRepository.findByFlyDestination((java.lang.String) map.get("CityPrimaryKey"));
            if (listofflyDestination.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("FlightPrimaryKey"));
            flightRepository.findById((java.lang.String) map.get("FlightPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("FlightPrimaryKey"));
            flightRepository.delete((java.lang.String) map.get("FlightPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateFlight(EntityTestCriteria contraints, Flight flight) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            flight.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            flight.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            flight.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            flightRepository.save(flight);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "flightName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "flightName", "zlmjGSJ8Kz3dHwmdB2vrgxm3w7LFm4BHayoTaJXCsNVmPQaCDWY72K8JsOfizlC44YLMNedtrMlJwwPYzJaFi8a1ELBLZIHOgEG7an6ck6NeqMQNA6rsamATD2JrQudO6hYF07NJFqRex7N43mOOFGnoWM6T9hy8NBaz5MGxr7VZZH6fl1eQRXff0c9YcrK17ACyaoJFzqvy9193YtIK85OArBGZSRyBS1NBnZ4DcRNIh7jERdGEyczDaTGToYwn1"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Flight flight = createFlight(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = flight.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(flight, null);
                        validateFlight(contraints, flight);
                        failureCount++;
                        break;
                    case 2:
                        flight.setFlightName(contraints.getNegativeValue().toString());
                        validateFlight(contraints, flight);
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
