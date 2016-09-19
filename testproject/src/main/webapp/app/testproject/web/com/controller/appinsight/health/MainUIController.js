Ext.define('Testproject.testproject.web.com.controller.appinsight.health.MainUIController', {
     extend: 'Testproject.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.MainUIController',
     onButtonClick: function(me, e, eOpts) {
          var formComponent = new Ext.create('Testproject.testproject.web.com.view.appinsight.health.TestUi', {
               header: false
          });
          var valuesArray = [];
          valuesArray.push({
               'key': '#empNo_displayfield',
               'value': this.view.down('#empNo_numberfield').getValue()
          });
          formComponent.controller.setDefaultData(valuesArray);
          this.createWindow(formComponent, 'TestUi');
     }
});