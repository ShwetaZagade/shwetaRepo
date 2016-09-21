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
import java.sql.Date;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

@Table(name = "Passanger")
@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "multiTenantId", contextProperty = "tenant.id")
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Passanger", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Passanger.findAll", query = " select u from Passanger u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Passanger.findByPassangerAddr", query = "select e from Passanger e where e.systemInfo.activeStatus=1 and e.passangerAddr=:passangerAddr"), @javax.persistence.NamedQuery(name = "Passanger.findById", query = "select e from Passanger e where e.systemInfo.activeStatus=1 and e.passangerId =:passangerId") })
public class Passanger implements Serializable, CommonEntityInterface, Comparator<Passanger> {

    private static final long serialVersionUID = 1474447890563L;

    @Column(name = "passangerName")
    @JsonProperty("passangerName")
    @NotNull
    @Size(min = 1, max = 256)
    private String passangerName;

    @Column(name = "passportNo")
    @JsonProperty("passportNo")
    @NotNull
    @Size(min = 1, max = 256)
    private String passportNo;

    @Column(name = "dob")
    @JsonProperty("dob")
    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date dob;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "passangerId")
    @JsonProperty("passangerId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String passangerId;

    @Column(name = "passangerAddr")
    @JsonProperty("passangerAddr")
    private String passangerAddr;

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

    public String getPassangerName() {
        return passangerName;
    }

    public void setPassangerName(String _passangerName) {
        if (_passangerName != null) {
            this.passangerName = _passangerName;
        }
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String _passportNo) {
        if (_passportNo != null) {
            this.passportNo = _passportNo;
        }
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date _dob) {
        this.dob = _dob;
    }

    public String getPrimaryKey() {
        return passangerId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return passangerId;
    }

    public String getPassangerId() {
        return passangerId;
    }

    public void setPassangerId(String _passangerId) {
        this.passangerId = _passangerId;
    }

    public String getPassangerAddr() {
        return passangerAddr;
    }

    public void setPassangerAddr(String _passangerAddr) {
        this.passangerAddr = _passangerAddr;
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
     * @param Passanger
     * @param Passanger
     */
    @Override
    public int compare(Passanger object1, Passanger object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((passangerName == null ? " " : passangerName) + ",").append((passportNo == null ? " " : passportNo));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (passangerId == null) {
            return super.hashCode();
        } else {
            return passangerId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.airlineboundedcontext.reservedomain.Passanger other = (com.app.shared.airlineboundedcontext.reservedomain.Passanger) obj;
            if (passangerId == null) {
                return false;
            } else if (!passangerId.equals(other.passangerId)) {
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
