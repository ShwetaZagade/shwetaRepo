Ext.define('Testpro.testpro.web.com.view.appinsight.health.Aui', {
     "xtype": "auiView",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "tnm",
          "margin": 5,
          "bindable": "tnm",
          "name": "tnm",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tnm_textfield"
     }, {
          "xtype": "textfield",
          "fieldLabel": "tDate",
          "margin": 5,
          "bindable": "tDate",
          "name": "tDate",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tDate_textfield"
     }, {
          "xtype": "textfield",
          "fieldLabel": "tDateTime",
          "margin": 5,
          "bindable": "tDateTime",
          "name": "tDateTime",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "tDateTime_textfield"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_8259",
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt8259Afterrender",
          "scope": "controller"
     },
     "requires": ["Testpro.testpro.web.com.controller.appinsight.health.AuiController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.AuiViewModel", "Testpro.testpro.shared.com.model.appinsight.health.AuiModel"],
     "viewModel": "AuiViewModel",
     "controller": "AuiController"
});