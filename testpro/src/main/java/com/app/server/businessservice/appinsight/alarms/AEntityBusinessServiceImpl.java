package com.app.server.businessservice.appinsight.alarms;
import com.app.server.repository.appinsight.alarms.AEntityRepository;
import com.app.shared.appinsight.alarms.AEntity;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AEntityBusinessServiceImpl implements AEntityBusinessService {

    @Autowired
    private AEntityRepository aEntityRepository;

    /**
     * Update the <AEntity> object
     * @Params Object of AEntity
     * @throws java.lang.Exception
     */
    @Override
    public void update(AEntity entity) throws Exception {
        if (entity.isHardDelete()) {
            aEntityRepository.delete(entity.getAid());
        } else {
            aEntityRepository.deleteTestA(entity.getDeletedTestAList());
            aEntityRepository.update(entity);
        }
    }

    /**
     * Update the list of <AEntity> object
     * @Params List of AEntity Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<AEntity> entity) throws Exception {
        for (AEntity _aentity : entity) {
            if (_aentity.isHardDelete()) {
                aEntityRepository.delete(_aentity.getAid());
            } else {
                aEntityRepository.deleteTestA(_aentity.getDeletedTestAList());
                aEntityRepository.update(_aentity);
            }
        }
    }
}
