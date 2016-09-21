Ext.define('Testpro.testpro.web.com.view.appinsight.health.Bui', {
     "xtype": "buiView",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "TextField",
          "margin": 5,
          "bindable": "tnm",
          "name": "tnm",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tnm_textfield"
     }, {
          "xtype": "datefields",
          "fieldLabel": "DateField",
          "name": "tDate",
          "bindable": "tDate",
          "margin": 5,
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tDate_datefield",
          "submitFormat": "d-m-Y"
     }, {
          "xtype": "customdatetimefield",
          "fieldLabel": "Data & Time",
          "name": "tDateTime",
          "bindable": "tDateTime",
          "margin": 5,
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tDateTime_customdatetimefield",
          "submitFormat": "d-m-Y H:m:s"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_9940",
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt9940Afterrender",
          "scope": "controller"
     },
     "requires": ["Testpro.testpro.web.com.controller.appinsight.health.BuiController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.BuiViewModel", "Testpro.testpro.shared.com.model.appinsight.health.BuiModel", "Testpro.view.fw.component.DateTimeField", "Testpro.view.fw.component.DateTimePicker", "Testpro.view.fw.component.DateFields"],
     "viewModel": "BuiViewModel",
     "controller": "BuiController"
});