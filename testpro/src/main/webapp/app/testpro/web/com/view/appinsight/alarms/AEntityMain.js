Ext.define('Testpro.testpro.web.com.view.appinsight.alarms.AEntityMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "aEntityMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AEntityMainController",
     "restURL": "/AEntity",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.alarms.AEntityModel", "Testpro.testpro.web.com.controller.appinsight.alarms.AEntityMainController", "Testpro.view.fw.component.DateTimeField", "Testpro.view.fw.component.DateTimePicker", "Testpro.testpro.shared.com.viewmodel.appinsight.alarms.AEntityViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "displayName": "AEntity",
               "name": "AEntityTreeContainer",
               "itemId": "AEntityTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "AEntityTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "xtype": "form",
                    "displayName": "AEntity",
                    "name": "AEntity",
                    "itemId": "AEntityForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "aid",
                                   "itemId": "aid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "aid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "aid<font color='red'> *<\/font>",
                                   "fieldId": "821F6BF0-93F1-4EEC-93C0-9AEAB9085C7F",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "aid"
                              }, {
                                   "name": "aNm",
                                   "itemId": "aNm",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "aNm",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "aNm<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "0A902AE1-E3F6-4FE5-8A7A-16B48641E6C9",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "aNm",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "aDate",
                                   "itemId": "aDate",
                                   "xtype": "customdatetimefield",
                                   "customWidgetType": "customdatetimefield",
                                   "displayName": "aDate",
                                   "margin": "5 5 5 5",
                                   "submitFormat": "d-m-Y H:i:s",
                                   "format": "d-m-Y H:i:s",
                                   "fieldLabel": "aDate<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "FD848E1C-38B8-40D1-BEC6-B5199490A65D",
                                   "bindable": "aDate",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "versionId",
                                   "itemId": "versionId",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "versionId",
                                   "margin": "5 5 5 5",
                                   "value": "-1",
                                   "fieldLabel": "versionId",
                                   "fieldId": "F8A624B8-E088-4CB1-AD17-3D02AA55B175",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "AEntity",
                    "title": "Details Grid",
                    "name": "AEntityGrid",
                    "itemId": "AEntityGrid",
                    "requires": [],
                    "columns": [{
                         "header": "aid",
                         "dataIndex": "aid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "aNm",
                         "dataIndex": "aNm",
                         "flex": 1
                    }, {
                         "header": "aDate",
                         "dataIndex": "aDate",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "multiTenantId",
                         "dataIndex": "multiTenantId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "xtype": "form",
               "displayName": "AEntity",
               "name": "AEntity",
               "itemId": "AEntityForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "aid",
                              "itemId": "aid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "aid",
                              "margin": "5 5 5 5",
                              "fieldLabel": "aid<font color='red'> *<\/font>",
                              "fieldId": "821F6BF0-93F1-4EEC-93C0-9AEAB9085C7F",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "aid"
                         }, {
                              "name": "aNm",
                              "itemId": "aNm",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "aNm",
                              "margin": "5 5 5 5",
                              "fieldLabel": "aNm<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "0A902AE1-E3F6-4FE5-8A7A-16B48641E6C9",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "aNm",
                              "columnWidth": 0.5
                         }, {
                              "name": "aDate",
                              "itemId": "aDate",
                              "xtype": "customdatetimefield",
                              "customWidgetType": "customdatetimefield",
                              "displayName": "aDate",
                              "margin": "5 5 5 5",
                              "submitFormat": "d-m-Y H:i:s",
                              "format": "d-m-Y H:i:s",
                              "fieldLabel": "aDate<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "FD848E1C-38B8-40D1-BEC6-B5199490A65D",
                              "bindable": "aDate",
                              "columnWidth": 0.5
                         }, {
                              "name": "versionId",
                              "itemId": "versionId",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "versionId",
                              "margin": "5 5 5 5",
                              "value": "-1",
                              "fieldLabel": "versionId",
                              "fieldId": "F8A624B8-E088-4CB1-AD17-3D02AA55B175",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});