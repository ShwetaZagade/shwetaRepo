Ext.define('Testproject.testproject.web.com.view.appinsight.health.MainUI', {
     "xtype": "mainUIView",
     "items": [{
          "xtype": "numberfield",
          "fieldLabel": "Emp Number",
          "name": "empNo",
          "margin": 5,
          "bindable": "empNo",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "empNo_numberfield"
     }, {
          "xtype": "button",
          "name": "Button",
          "text": "Button",
          "margin": 5,
          "scale": "medium",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "Button_button",
          "listeners": {
               "click": "onButtonClick"
          }
     }],
     "layout": {
          "type": "column"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_7365",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Testproject.testproject.web.com.controller.appinsight.health.MainUIController", "Testproject.testproject.shared.com.viewmodel.appinsight.health.MainUIViewModel", "Testproject.testproject.shared.com.model.appinsight.health.MainUIModel"],
     "viewModel": "MainUIViewModel",
     "controller": "MainUIController"
});