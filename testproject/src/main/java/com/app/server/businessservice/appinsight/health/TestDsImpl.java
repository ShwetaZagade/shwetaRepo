package com.app.server.businessservice.appinsight.health;
import com.app.server.businessservice.appinsight.AQBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AQRM;
import com.app.shared.appinsight.health.TestDto;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "3", comments = "TestDsImpl", complexity = Complexity.HIGH)
public class TestDsImpl implements TestDs {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private AQBzService aQBzService;

    @Override
    public AQRM proTestDs(TestDto ds) throws BusinessRuleUnableToProceedException, Exception {
        java.util.List<com.app.shared.appinsight.AQRM> aQRMList = aQBzService.executeQueryAQ(ds.getEmpNo());
        for (com.app.shared.appinsight.AQRM aQRMListElement : aQRMList) {
            return aQRMListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}
