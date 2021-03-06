package com.app.shared.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Emp_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "5", comments = "Emp", complexity = Complexity.LOW)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "empId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "Emp.findAll", query = " select u from Emp u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Emp.findById", query = "select e from Emp e where e.systemInfo.activeStatus=1 and e.empId =:empId") })
public class Emp implements Serializable, CommonEntityInterface, Comparator<Emp> {

    private static final long serialVersionUID = 1474276615637L;

    @Column(name = "empNo")
    @JsonProperty("empNo")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer empNo;

    @Column(name = "empNm")
    @JsonProperty("empNm")
    @NotNull
    @Size(min = 1, max = 256)
    private String empNm;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "empId")
    @JsonProperty("empId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String empId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "emp")
    private List<EmpDEpt> empDEpt;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer _empNo) {
        if (_empNo != null) {
            this.empNo = _empNo;
        }
    }

    public String getEmpNm() {
        return empNm;
    }

    public void setEmpNm(String _empNm) {
        if (_empNm != null) {
            this.empNm = _empNm;
        }
    }

    public String getPrimaryKey() {
        return empId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String _empId) {
        this.empId = _empId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
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

    public Emp addEmpDEpt(EmpDEpt _empDEpt) {
        if (this.empDEpt == null) {
            this.empDEpt = new java.util.ArrayList<com.app.shared.appinsight.health.EmpDEpt>();
        }
        if (_empDEpt != null) {
            _empDEpt.setEmp(this);
            this.empDEpt.add(_empDEpt);
        }
        return this;
    }

    public Emp removeEmpDEpt(EmpDEpt _empDEpt) {
        if (this.empDEpt != null) {
            this.empDEpt.remove(_empDEpt);
        }
        return this;
    }

    public Emp addAllEmpDEpt(List<EmpDEpt> _empDEpt) {
        if (this.empDEpt == null) {
            this.empDEpt = new java.util.ArrayList<com.app.shared.appinsight.health.EmpDEpt>();
        }
        if (_empDEpt != null) {
            for (int i = 0; i < _empDEpt.size(); i++) {
                _empDEpt.get(i).setEmp(this);
            }
            this.empDEpt.addAll(_empDEpt);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfEmpDEpt() {
        if (this.empDEpt != null) {
            return this.empDEpt.size();
        }
        return 0;
    }

    public List<EmpDEpt> getEmpDEpt() {
        return empDEpt;
    }

    public void setEmpDEpt(List<EmpDEpt> _empDEpt) {
        for (int i = 0; i < _empDEpt.size(); i++) {
            if (_empDEpt.get(i).getEmp() == null) {
                _empDEpt.get(i).setEmp(this);
            }
        }
        this.empDEpt = _empDEpt;
    }

    @JsonIgnore
    public List<EmpDEpt> getDeletedEmpDEptList() {
        List<EmpDEpt> empdeptToRemove = new java.util.ArrayList<EmpDEpt>();
        for (EmpDEpt _empdept : empDEpt) {
            if (_empdept.isHardDelete()) {
                empdeptToRemove.add(_empdept);
            }
        }
        empDEpt.removeAll(empdeptToRemove);
        return empdeptToRemove;
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
        setValidatorempDEpt(_validateFactory);
    }

    private void setValidatorempDEpt(EntityValidatorHelper<Object> _validateFactory) {
        if (empDEpt != null) {
            for (int i = 0; i < empDEpt.size(); i++) {
                empDEpt.get(i).setEntityValidator(_validateFactory);
            }
        }
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
        if (this.empDEpt == null) {
            this.empDEpt = new java.util.ArrayList<EmpDEpt>();
        }
        for (EmpDEpt _empDEpt : empDEpt) {
            _empDEpt.setEntityAudit(customerId, userId, recordType);
            _empDEpt.setSystemInformation(recordType);
        }
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
        if (this.empDEpt == null) {
            this.empDEpt = new java.util.ArrayList<EmpDEpt>();
        }
        for (EmpDEpt _empDEpt : empDEpt) {
            _empDEpt.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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
     * @param Emp
     * @param Emp
     */
    @Override
    public int compare(Emp object1, Emp object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((empNm == null ? " " : empNm));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (empId == null) {
            return super.hashCode();
        } else {
            return empId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appinsight.health.Emp other = (com.app.shared.appinsight.health.Emp) obj;
            if (empId == null) {
                return false;
            } else if (!empId.equals(other.empId)) {
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
