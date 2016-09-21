package com.app.server.businessservice.appinsight.alarms;
import com.app.shared.appinsight.alarms.AEntity;
import java.util.List;

public interface AEntityBusinessService {

    void update(AEntity entity) throws Exception;

    void update(List<AEntity> entity) throws Exception;
}
