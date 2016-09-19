Ext.define('Testproject.testproject.web.com.controller.appinsight.health.TestUiController', {
     extend: 'Testproject.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.TestUiController',
     onFormExt7183Afterrender: function(me, eOpts) {
          var jsonData = {};
          jsonData.empNo = this.view.down('#empNo_displayfield').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/TestDsWS/proTestDs',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#empNm_displayfield').setValue(responseData.empNm);
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