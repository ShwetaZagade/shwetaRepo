Ext.define('Testpro.testpro.web.com.controller.appinsight.health.BuiController', {
     extend: 'Testpro.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.BuiController',
     onFormExt9940Afterrender: function(me, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/AdsWS/pAds',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#tDate_datefield').setValues(responseData.tDate);
                         scope.sender.down('#tDateTime_customdatetimefield').setValues(responseData.tDateTime);
                         scope.sender.down('#tnm_textfield').setValue(responseData.tnm);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});