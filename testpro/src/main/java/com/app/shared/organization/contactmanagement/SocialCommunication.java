package com.app.shared.organization.contactmanagement;
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

@Table(name = "SocialCommunication")
@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "multiTenantId", contextProperty = "tenant.id")
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "SocialCommunication", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SocialCommunication.findAll", query = " select u from SocialCommunication u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "SocialCommunication.findByCommType", query = "select e from SocialCommunication e where e.systemInfo.activeStatus=1 and e.commType=:commType"), @javax.persistence.NamedQuery(name = "SocialCommunication.findById", query = "select e from SocialCommunication e where e.systemInfo.activeStatus=1 and e.socialId =:socialId") })
public class SocialCommunication implements Serializable, CommonEntityInterface, Comparator<SocialCommunication> {

    private static final long serialVersionUID = 1474436553295L;

    @Column(name = "social")
    @JsonProperty("social")
    @NotNull
    @Size(min = 1, max = 256)
    private String social;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "socialId")
    @JsonProperty("socialId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String socialId;

    @Column(name = "commType")
    @JsonProperty("commType")
    private String commType;

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

    public String getSocial() {
        return social;
    }

    public void setSocial(String _social) {
        if (_social != null) {
            this.social = _social;
        }
    }

    public String getPrimaryKey() {
        return socialId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return socialId;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String _socialId) {
        this.socialId = _socialId;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String _commType) {
        this.commType = _commType;
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
     * @param SocialCommunication
     * @param SocialCommunication
     */
    @Override
    public int compare(SocialCommunication object1, SocialCommunication object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (socialId == null) {
            return super.hashCode();
        } else {
            return socialId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.contactmanagement.SocialCommunication other = (com.app.shared.organization.contactmanagement.SocialCommunication) obj;
            if (socialId == null) {
                return false;
            } else if (!socialId.equals(other.socialId)) {
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
