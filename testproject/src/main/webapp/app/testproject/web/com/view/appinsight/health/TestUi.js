Ext.define('Testproject.testproject.web.com.view.appinsight.health.TestUi', {
     "xtype": "testUiView",
     "items": [{
          "xtype": "displayfield",
          "fieldLabel": "Name",
          "margin": 5,
          "bindable": "empNm",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "empNm",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "empNm_displayfield"
     }, {
          "xtype": "displayfield",
          "fieldLabel": "No",
          "margin": 5,
          "bindable": "empNo",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "empNo",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "empNo_displayfield"
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "EmpInfo",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_7183",
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt7183Afterrender",
          "scope": "controller"
     },
     "requires": ["Testproject.testproject.web.com.controller.appinsight.health.TestUiController", "Testproject.testproject.shared.com.viewmodel.appinsight.health.TestUiViewModel", "Testproject.testproject.shared.com.model.appinsight.health.TestUiModel"],
     "viewModel": "TestUiViewModel",
     "controller": "TestUiController"
});