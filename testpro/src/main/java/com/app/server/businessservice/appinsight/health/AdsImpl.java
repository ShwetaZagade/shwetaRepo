package com.app.server.businessservice.appinsight.health;
import com.app.server.businessservice.appinsight.AQBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AAQRM;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;
import java.lang.Override;

@Component
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "6", comments = "AdsImpl", complexity = Complexity.HIGH)
public class AdsImpl implements Ads {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private AQBzService aQBzService;

    @Override
    public AAQRM pAds() throws BusinessRuleUnableToProceedException, Exception {
        java.util.List<com.app.shared.appinsight.AAQRM> aAQRMList = aQBzService.executeQueryAAQ();
        for (com.app.shared.appinsight.AAQRM aAQRMListElement : aAQRMList) {
            return aAQRMListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}
