package com.app.server.businessservice.appinsight.health;
import com.app.shared.appinsight.health.Emp;
import java.util.List;

public interface EmpBusinessService {

    void update(Emp entity) throws Exception;

    void update(List<Emp> entity) throws Exception;
}
