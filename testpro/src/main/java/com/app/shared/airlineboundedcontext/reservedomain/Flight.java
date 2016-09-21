package com.app.shared.airlineboundedcontext.reservedomain;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import java.sql.Date;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Time;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Flight")
@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "multiTenantId", contextProperty = "tenant.id")
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "4", comments = "Flight", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Flight.findAll", query = " select u from Flight u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Flight.findByFlySource", query = "select e from Flight e where e.systemInfo.activeStatus=1 and e.flySource=:flySource"), @javax.persistence.NamedQuery(name = "Flight.findByFlyDestination", query = "select e from Flight e where e.systemInfo.activeStatus=1 and e.flyDestination=:flyDestination"), @javax.persistence.NamedQuery(name = "Flight.findById", query = "select e from Flight e where e.systemInfo.activeStatus=1 and e.flightId =:flightId") })
public class Flight implements Serializable, CommonEntityInterface, Comparator<Flight> {

    private static final long serialVersionUID = 1474447892208L;

    @Column(name = "flightName")
    @JsonProperty("flightName")
    @NotNull
    @Size(min = 1, max = 256)
    private String flightName;

    @Column(name = "totalSeat")
    @JsonProperty("totalSeat")
    @Max(2147483647)
    private Integer totalSeat;

    @Column(name = "departureDate")
    @JsonProperty("departureDate")
    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date departureDate;

    @Column(name = "departureTime")
    @JsonProperty("departureTime")
    private Time departureTime;

    @Column(name = "fareEconomy")
    @JsonProperty("fareEconomy")
    @Max(2147483647)
    private Integer fareEconomy;

    @Column(name = "fareFirst")
    @JsonProperty("fareFirst")
    @Max(2147483647)
    private Integer fareFirst;

    @Column(name = "bookEcon")
    @JsonProperty("bookEcon")
    @Max(2147483647)
    private Integer bookEcon;

    @Column(name = "bookFirst")
    @JsonProperty("bookFirst")
    @Max(2147483647)
    private Integer bookFirst;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "flightId")
    @JsonProperty("flightId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String flightId;

    @Column(name = "flySource")
    @JsonProperty("flySource")
    private String flySource;

    @Column(name = "flyDestination")
    @JsonProperty("flyDestination")
    private String flyDestination;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Column(name = "multiTenantId", insertable = false, updatable = false)
    @JsonProperty("multiTenantId")
    private String multiTenantId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String _flightName) {
        if (_flightName != null) {
            this.flightName = _flightName;
        }
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer _totalSeat) {
        this.totalSeat = _totalSeat;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date _departureDate) {
        this.departureDate = _departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time _departureTime) {
        this.departureTime = _departureTime;
    }

    public Integer getFareEconomy() {
        return fareEconomy;
    }

    public void setFareEconomy(Integer _fareEconomy) {
        this.fareEconomy = _fareEconomy;
    }

    public Integer getFareFirst() {
        return fareFirst;
    }

    public void setFareFirst(Integer _fareFirst) {
        this.fareFirst = _fareFirst;
    }

    public Integer getBookEcon() {
        return bookEcon;
    }

    public void setBookEcon(Integer _bookEcon) {
        this.bookEcon = _bookEcon;
    }

    public Integer getBookFirst() {
        return bookFirst;
    }

    public void setBookFirst(Integer _bookFirst) {
        this.bookFirst = _bookFirst;
    }

    public String getPrimaryKey() {
        return flightId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return flightId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String _flightId) {
        this.flightId = _flightId;
    }

    public String getFlySource() {
        return flySource;
    }

    public void setFlySource(String _flySource) {
        this.flySource = _flySource;
    }

    public String getFlyDestination() {
        return flyDestination;
    }

    public void setFlyDestination(String _flyDestination) {
        this.flyDestination = _flyDestination;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public String getMultiTenantId() {
        return multiTenantId;
    }

    public void setMultiTenantId(String _multiTenantId) {
        this.multiTenantId = _multiTenantId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param _validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets created by, updated by, active status values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @Params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Flight
     * @param Flight
     */
    @Override
    public int compare(Flight object1, Flight object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((flightName == null ? " " : flightName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (flightId == null) {
            return super.hashCode();
        } else {
            return flightId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.airlineboundedcontext.reservedomain.Flight other = (com.app.shared.airlineboundedcontext.reservedomain.Flight) obj;
            if (flightId == null) {
                return false;
            } else if (!flightId.equals(other.flightId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
