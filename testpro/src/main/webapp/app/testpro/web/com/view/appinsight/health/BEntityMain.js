Ext.define('Testpro.testpro.web.com.view.appinsight.health.BEntityMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "bEntityMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "BEntityMainController",
     "restURL": "/BEntity",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.BEntityModel", "Testpro.testpro.web.com.controller.appinsight.health.BEntityMainController", "Testpro.view.fw.component.DateTimeField", "Testpro.view.fw.component.DateTimePicker", "Testpro.testpro.shared.com.viewmodel.appinsight.health.BEntityViewModel"],
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
               "displayName": "BEntity",
               "name": "BEntityTreeContainer",
               "itemId": "BEntityTreeContainer",
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
                    "itemId": "BEntityTree",
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
                    "displayName": "BEntity",
                    "name": "BEntity",
                    "itemId": "BEntityForm",
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
                                   "name": "bid",
                                   "itemId": "bid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "bid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "bid<font color='red'> *<\/font>",
                                   "fieldId": "FA19BDD4-9D35-431A-911D-5CA6C6C7F92D",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "bid"
                              }, {
                                   "name": "bNm",
                                   "itemId": "bNm",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "bNm",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "bNm<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "40AE1437-BBE8-4337-9180-B9902077A39C",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "bNm",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "bno",
                                   "itemId": "bno",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "bno",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "bno<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "F175DE17-2887-48D9-8F07-FFFB70EC8C3D",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "bno",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "cDate",
                                   "itemId": "cDate",
                                   "xtype": "customdatetimefield",
                                   "customWidgetType": "customdatetimefield",
                                   "displayName": "cDate",
                                   "margin": "5 5 5 5",
                                   "submitFormat": "d-m-Y H:i:s",
                                   "format": "d-m-Y H:i:s",
                                   "fieldLabel": "cDate<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "D52C2316-7485-43F1-A0BC-F6DBF0CFE5F3",
                                   "bindable": "cDate",
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
                                   "fieldId": "BE08FCB6-CC54-44CF-81AC-D0FEF4B26A5E",
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
                    "displayName": "BEntity",
                    "title": "Details Grid",
                    "name": "BEntityGrid",
                    "itemId": "BEntityGrid",
                    "requires": [],
                    "columns": [{
                         "header": "bid",
                         "dataIndex": "bid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "bNm",
                         "dataIndex": "bNm",
                         "flex": 1
                    }, {
                         "header": "bno",
                         "dataIndex": "bno",
                         "flex": 1
                    }, {
                         "header": "cDate",
                         "dataIndex": "cDate",
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
               "displayName": "BEntity",
               "name": "BEntity",
               "itemId": "BEntityForm",
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
                              "name": "bid",
                              "itemId": "bid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "bid",
                              "margin": "5 5 5 5",
                              "fieldLabel": "bid<font color='red'> *<\/font>",
                              "fieldId": "FA19BDD4-9D35-431A-911D-5CA6C6C7F92D",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "bid"
                         }, {
                              "name": "bNm",
                              "itemId": "bNm",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "bNm",
                              "margin": "5 5 5 5",
                              "fieldLabel": "bNm<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "40AE1437-BBE8-4337-9180-B9902077A39C",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "bNm",
                              "columnWidth": 0.5
                         }, {
                              "name": "bno",
                              "itemId": "bno",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "bno",
                              "margin": "5 5 5 5",
                              "fieldLabel": "bno<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "F175DE17-2887-48D9-8F07-FFFB70EC8C3D",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "bno",
                              "columnWidth": 0.5
                         }, {
                              "name": "cDate",
                              "itemId": "cDate",
                              "xtype": "customdatetimefield",
                              "customWidgetType": "customdatetimefield",
                              "displayName": "cDate",
                              "margin": "5 5 5 5",
                              "submitFormat": "d-m-Y H:i:s",
                              "format": "d-m-Y H:i:s",
                              "fieldLabel": "cDate<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "D52C2316-7485-43F1-A0BC-F6DBF0CFE5F3",
                              "bindable": "cDate",
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
                              "fieldId": "BE08FCB6-CC54-44CF-81AC-D0FEF4B26A5E",
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