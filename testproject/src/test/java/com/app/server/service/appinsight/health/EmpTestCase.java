package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.EmpRepository;
import com.app.shared.appinsight.health.Emp;
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
import com.app.shared.appinsight.health.EmpDEpt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class EmpTestCase extends EntityTestCriteria {

    /**
     * EmpRepository Variable
     */
    @Autowired
    private EmpRepository<Emp> empRepository;

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

    private Emp createEmp(Boolean isSave) throws Exception {
        Emp emp = new Emp();
        emp.setEmpNo(2147483647);
        emp.setEmpNm("wMqciGaCSPf4Eea3n4xzWpOSP7FUxqKGksoZVPoqedRJdKRphK");
        java.util.List<EmpDEpt> listOfEmpDEpt = new java.util.ArrayList<EmpDEpt>();
        EmpDEpt empdept = new EmpDEpt();
        empdept.setDept("vEkIIznZ3rN1B9jplsEFnMkEQc9BoZCjJKFPUIbKtLnG4jVi6u");
        empdept.setDept("m9JPymxxLD6TKC7FJa2jIrvdIr1MOxHqInD5Y4nJoQd5W5qeFL");
        empdept.setEmp(emp);
        listOfEmpDEpt.add(empdept);
        emp.addAllEmpDEpt(listOfEmpDEpt);
        emp.setEntityValidator(entityValidator);
        return emp;
    }

    @Test
    public void test1Save() {
        try {
            Emp emp = createEmp(true);
            emp.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            emp.isValid();
            empRepository.save(emp);
            map.put("EmpPrimaryKey", emp._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("EmpPrimaryKey"));
            Emp emp = empRepository.findById((java.lang.String) map.get("EmpPrimaryKey"));
            emp.setEmpNo(2147483647);
            emp.setVersionId(1);
            emp.setEmpNm("Nd5LMTeq81mZNb5tlMk1qbvDzTKIHyJw2GfMJwkbPuNjVnGqf6");
            emp.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            empRepository.update(emp);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("EmpPrimaryKey"));
            empRepository.findById((java.lang.String) map.get("EmpPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("EmpPrimaryKey"));
            empRepository.delete((java.lang.String) map.get("EmpPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateEmp(EntityTestCriteria contraints, Emp emp) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            emp.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            emp.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            emp.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            empRepository.save(emp);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "empNo", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 2, "empNm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "empNm", "kAiWxxd8Ln9uKUQNQlRP3x3hiUNHsLslukUTGEsdhHSgRsIu6jtt8WDslXzo8epiSVpC6doAbSablqGJ9shrQ0BGh1yAm6EJ5UybclZfZBPWuRk6I9Vhq59JmJaQjxhO5m3Tu2ysyRhbg4RxsWluXPMdJz81xUXejni40r91pz2r8PrxfwOJohrPimjPDtkTfYSqU3SVy1lDVZOaoriGuFOv1T7pfzxeozgnrKk10MJB33hnx1uR6dJuw5RokdbLO"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Emp emp = createEmp(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = emp.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(emp, null);
                        validateEmp(contraints, emp);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(emp, null);
                        validateEmp(contraints, emp);
                        failureCount++;
                        break;
                    case 3:
                        emp.setEmpNm(contraints.getNegativeValue().toString());
                        validateEmp(contraints, emp);
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
