package com.app.server.repository.airlineboundedcontext.reservedomain;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.airlineboundedcontext.reservedomain.Flight;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Override;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Repository for Flight Transaction table", complexity = Complexity.MEDIUM)
public class FlightRepositoryImpl extends SearchInterfaceImpl implements FlightRepository<Flight> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Flight> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<Flight> query = emanager.createNamedQuery("Flight.findAll").getResultList();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Flight> object.
     * @return Flight
     * @Params Object of Flight
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Flight save(Flight entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("ABCHU322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Flight> object.
     * @return java.util.List<Flight>
     * @Params list of Flight
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Flight> save(List<Flight> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Flight obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABCHU322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Flight> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Flight object = emanager.find(com.app.shared.airlineboundedcontext.reservedomain.Flight.class, id);
        emanager.remove(object);
        Log.out.println("ABCHU328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Flight> object.
     * @Params Object of Flight
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Flight entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("ABCHU321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Flight> object.
     * @Params list of Flight
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Flight> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Flight obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABCHU321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Flight objects by filtering on refernce key <flySource>
     * @return List<Flight>
     * @Params flySource of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Flight> findByFlySource(String flySource) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Flight.findByFlySource");
        query.setParameter("flySource", flySource);
        java.util.List<com.app.shared.airlineboundedcontext.reservedomain.Flight> listOfFlight = query.getResultList();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "findByFlySource", "Total Records Fetched = " + listOfFlight.size());
        return listOfFlight;
    }

    /**
     * Return list of Flight objects by filtering on refernce key <flyDestination>
     * @return List<Flight>
     * @Params flyDestination of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Flight> findByFlyDestination(String flyDestination) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Flight.findByFlyDestination");
        query.setParameter("flyDestination", flyDestination);
        java.util.List<com.app.shared.airlineboundedcontext.reservedomain.Flight> listOfFlight = query.getResultList();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "findByFlyDestination", "Total Records Fetched = " + listOfFlight.size());
        return listOfFlight;
    }

    /**
     * Return Flight object by filtering on refernce key <flightId>
     * @return Flight
     * @Params flightId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Flight findById(String flightId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Flight.findById");
        query.setParameter("flightId", flightId);
        Flight listOfFlight = (Flight) query.getSingleResult();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "FlightRepositoryImpl", "findById", "Total Records Fetched = " + listOfFlight);
        return listOfFlight;
    }
}
