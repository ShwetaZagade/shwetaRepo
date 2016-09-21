package com.app.server.businessservice.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AAQRM;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "6", comments = "Ads", complexity = Complexity.HIGH)
public interface Ads {

    public AAQRM pAds() throws BusinessRuleUnableToProceedException, Exception;
}
