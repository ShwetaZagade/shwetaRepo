package com.app.server.businessservice.appinsight.health;
import com.app.server.repository.appinsight.health.EmpRepository;
import com.app.shared.appinsight.health.Emp;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmpBusinessServiceImpl implements EmpBusinessService {

    @Autowired
    private EmpRepository empRepository;

    /**
     * Update the <Emp> object
     * @Params Object of Emp
     * @throws java.lang.Exception
     */
    @Override
    public void update(Emp entity) throws Exception {
        if (entity.isHardDelete()) {
            empRepository.delete(entity.getEmpId());
        } else {
            empRepository.deleteEmpDEpt(entity.getDeletedEmpDEptList());
            empRepository.update(entity);
        }
    }

    /**
     * Update the list of <Emp> object
     * @Params List of Emp Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<Emp> entity) throws Exception {
        for (Emp _emp : entity) {
            if (_emp.isHardDelete()) {
                empRepository.delete(_emp.getEmpId());
            } else {
                empRepository.deleteEmpDEpt(_emp.getDeletedEmpDEptList());
                empRepository.update(_emp);
            }
        }
    }
}
