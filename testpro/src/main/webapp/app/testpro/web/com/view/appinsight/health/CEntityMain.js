Ext.define('Testpro.testpro.web.com.view.appinsight.health.CEntityMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "cEntityMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "CEntityMainController",
     "restURL": "/CEntity",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.CEntityModel", "Testpro.testpro.web.com.controller.appinsight.health.CEntityMainController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.CEntityViewModel"],
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
               "displayName": "CEntity",
               "name": "CEntityTreeContainer",
               "itemId": "CEntityTreeContainer",
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
                    "itemId": "CEntityTree",
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
                    "displayName": "CEntity",
                    "name": "CEntity",
                    "itemId": "CEntityForm",
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
                                   "name": "cid",
                                   "itemId": "cid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "cid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "cid<font color='red'> *<\/font>",
                                   "fieldId": "656266B3-36AA-4E33-83CF-759983A1009F",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "cid"
                              }, {
                                   "name": "cNm",
                                   "itemId": "cNm",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "cNm",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "cNm<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "24FF1BB5-280A-41EC-846D-AAF7D9188A27",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "cNm",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "cNo",
                                   "itemId": "cNo",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "cNo",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "cNo<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "AE0E27AD-374F-451F-B32C-FC8C0A920CAB",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "cNo",
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
                                   "fieldId": "29613E79-902D-4C95-8122-54FAAF0D8492",
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
                    "displayName": "CEntity",
                    "title": "Details Grid",
                    "name": "CEntityGrid",
                    "itemId": "CEntityGrid",
                    "requires": [],
                    "columns": [{
                         "header": "cid",
                         "dataIndex": "cid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "cNm",
                         "dataIndex": "cNm",
                         "flex": 1
                    }, {
                         "header": "cNo",
                         "dataIndex": "cNo",
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
               "displayName": "CEntity",
               "name": "CEntity",
               "itemId": "CEntityForm",
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
                              "name": "cid",
                              "itemId": "cid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "cid",
                              "margin": "5 5 5 5",
                              "fieldLabel": "cid<font color='red'> *<\/font>",
                              "fieldId": "656266B3-36AA-4E33-83CF-759983A1009F",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "cid"
                         }, {
                              "name": "cNm",
                              "itemId": "cNm",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "cNm",
                              "margin": "5 5 5 5",
                              "fieldLabel": "cNm<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "24FF1BB5-280A-41EC-846D-AAF7D9188A27",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "cNm",
                              "columnWidth": 0.5
                         }, {
                              "name": "cNo",
                              "itemId": "cNo",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "cNo",
                              "margin": "5 5 5 5",
                              "fieldLabel": "cNo<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "AE0E27AD-374F-451F-B32C-FC8C0A920CAB",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "cNo",
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
                              "fieldId": "29613E79-902D-4C95-8122-54FAAF0D8492",
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