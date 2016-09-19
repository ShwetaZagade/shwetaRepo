Ext.define('Testproject.testproject.web.com.view.appinsight.health.EmpMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "empMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "EmpMainController",
     "restURL": "/Emp",
     "defaults": {
          "split": true
     },
     "requires": ["Testproject.testproject.shared.com.model.appinsight.health.EmpModel", "Testproject.testproject.web.com.controller.appinsight.health.EmpMainController", "Testproject.view.fw.component.Grids", "Testproject.testproject.shared.com.viewmodel.appinsight.health.EmpViewModel"],
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
               "displayName": "Emp",
               "name": "EmpTreeContainer",
               "itemId": "EmpTreeContainer",
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
                    "itemId": "EmpTree",
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
                    "displayName": "Emp",
                    "name": "Emp",
                    "itemId": "EmpForm",
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
                                   "name": "empNo",
                                   "itemId": "empNo",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "empNo",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "empNo<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "19401B0D-1B5A-42B5-B746-FA9C2231188B",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "empNo",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "empId",
                                   "itemId": "empId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "empId",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "empId<font color='red'> *<\/font>",
                                   "fieldId": "5B2113B6-19EB-4D68-B3C5-A6695C46D61F",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "empId"
                              }, {
                                   "name": "empNm",
                                   "itemId": "empNm",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "empNm",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "empNm<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "97270197-CB87-4E7D-8C7B-73C1FCBDD8DD",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "empNm",
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
                                   "fieldId": "3ECA442C-0455-44D0-9D0E-08E137B70E93",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }, {
                         "xtype": "form",
                         "displayName": "EmpDEpt",
                         "title": "EmpDEpt",
                         "name": "EmpDEpt",
                         "itemId": "EmpDEptForm",
                         "bodyPadding": 10,
                         "items": [{
                              "xtype": "form",
                              "itemId": "form1",
                              "customWidgetType": "vdAnchorLayout",
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
                                        "name": "deptId",
                                        "itemId": "deptId",
                                        "xtype": "textfield",
                                        "customWidgetType": "vdTextfield",
                                        "displayName": "deptId",
                                        "margin": "5 5 5 5",
                                        "fieldLabel": "deptId<font color='red'> *<\/font>",
                                        "fieldId": "A866C3A5-63EF-4093-A482-15AE1E727FE1",
                                        "minLength": "1",
                                        "maxLength": "256",
                                        "hidden": true,
                                        "value": "",
                                        "bindable": "empDEpt.deptId"
                                   }, {
                                        "name": "dept",
                                        "itemId": "dept",
                                        "xtype": "textfield",
                                        "customWidgetType": "vdTextfield",
                                        "displayName": "dept",
                                        "margin": "5 5 5 5",
                                        "fieldLabel": "dept<font color='red'> *<\/font>",
                                        "allowBlank": false,
                                        "fieldId": "85712EBC-A029-4504-9316-C1D4406D80E0",
                                        "minLength": "1",
                                        "maxLength": "256",
                                        "bindable": "empDEpt.dept",
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
                                        "fieldId": "F0A1D4E9-EC3B-4996-ACCD-501E5718C3B6",
                                        "bindable": "empDEpt.versionId",
                                        "hidden": true
                                   }]
                              }]
                         }, {
                              "columnWidth": 1,
                              "xtype": "button",
                              "margin": 5,
                              "customWidgetType": "vdButton",
                              "maxWidth": 132,
                              "text": "Add EmpDEpt",
                              "handler": "addEmpDEpt"
                         }, {
                              "xtype": "grids",
                              "customWidgetType": "vdGrid",
                              "title": "EmpDEpt",
                              "columnWidth": 1,
                              "itemId": "EmpDEptGrid",
                              "fieldLabel": "List",
                              "bindLevel": "empDEpt",
                              "bindable": "empDEpt",
                              "listeners": {
                                   "select": "onEmpDEptGridItemClick"
                              },
                              "store": {
                                   "fields": [],
                                   "data": []
                              },
                              "columns": [{
                                   "header": "deptId",
                                   "text": "deptId",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "deptId",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "dept",
                                   "text": "dept",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "dept",
                                   "flex": 1
                              }, {
                                   "header": "createdBy",
                                   "text": "createdBy",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "createdBy",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "createdDate",
                                   "text": "createdDate",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "createdDate",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "updatedBy",
                                   "text": "updatedBy",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "updatedBy",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "updatedDate",
                                   "text": "updatedDate",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "updatedDate",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "versionId",
                                   "text": "versionId",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "versionId",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "activeStatus",
                                   "text": "activeStatus",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "activeStatus",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "txnAccessCode",
                                   "text": "txnAccessCode",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "txnAccessCode",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "xtype": "actioncolumn",
                                   "customWidgetType": "vdActionColumn",
                                   "width": 30,
                                   "sortable": false,
                                   "menuDisable": true,
                                   "items": [{
                                        "icon": "images/delete.gif",
                                        "tooltip": "Delete Record",
                                        "handler": "onDeleteActionColumnClick"
                                   }]
                              }]
                         }],
                         "customWidgetType": "vdCard"
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
                    }, {
                         "xtype": "toolbar",
                         "customWidgetType": "vdTBar",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "itemId": "cardPrev",
                              "text": "&laquo; Previous",
                              "handler": "showPreviousCard",
                              "disabled": true,
                              "margin": "0 5 0 5"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "itemId": "cardNext",
                              "text": "Next &raquo;",
                              "handler": "showNextCard",
                              "margin": "0 5 0 5"
                         }]
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Emp",
                    "title": "Details Grid",
                    "name": "EmpGrid",
                    "itemId": "EmpGrid",
                    "requires": [],
                    "columns": [{
                         "header": "empNo",
                         "dataIndex": "empNo",
                         "flex": 1
                    }, {
                         "header": "empId",
                         "dataIndex": "empId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "empNm",
                         "dataIndex": "empNm",
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
               "displayName": "Emp",
               "name": "Emp",
               "itemId": "EmpForm",
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
                              "name": "empNo",
                              "itemId": "empNo",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "empNo",
                              "margin": "5 5 5 5",
                              "fieldLabel": "empNo<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "19401B0D-1B5A-42B5-B746-FA9C2231188B",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "empNo",
                              "columnWidth": 0.5
                         }, {
                              "name": "empId",
                              "itemId": "empId",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "empId",
                              "margin": "5 5 5 5",
                              "fieldLabel": "empId<font color='red'> *<\/font>",
                              "fieldId": "5B2113B6-19EB-4D68-B3C5-A6695C46D61F",
                              "minLength": "1",
                              "maxLength": "256",
                              "hidden": true,
                              "value": "",
                              "bindable": "empId"
                         }, {
                              "name": "empNm",
                              "itemId": "empNm",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "empNm",
                              "margin": "5 5 5 5",
                              "fieldLabel": "empNm<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "97270197-CB87-4E7D-8C7B-73C1FCBDD8DD",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "empNm",
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
                              "fieldId": "3ECA442C-0455-44D0-9D0E-08E137B70E93",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }, {
                    "xtype": "form",
                    "displayName": "EmpDEpt",
                    "title": "EmpDEpt",
                    "name": "EmpDEpt",
                    "itemId": "EmpDEptForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form1",
                         "customWidgetType": "vdAnchorLayout",
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
                                   "name": "deptId",
                                   "itemId": "deptId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "deptId",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "deptId<font color='red'> *<\/font>",
                                   "fieldId": "A866C3A5-63EF-4093-A482-15AE1E727FE1",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "empDEpt.deptId"
                              }, {
                                   "name": "dept",
                                   "itemId": "dept",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "dept",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "dept<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "85712EBC-A029-4504-9316-C1D4406D80E0",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "empDEpt.dept",
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
                                   "fieldId": "F0A1D4E9-EC3B-4996-ACCD-501E5718C3B6",
                                   "bindable": "empDEpt.versionId",
                                   "hidden": true
                              }]
                         }]
                    }, {
                         "columnWidth": 1,
                         "xtype": "button",
                         "margin": 5,
                         "customWidgetType": "vdButton",
                         "maxWidth": 132,
                         "text": "Add EmpDEpt",
                         "handler": "addEmpDEpt"
                    }, {
                         "xtype": "grids",
                         "customWidgetType": "vdGrid",
                         "title": "EmpDEpt",
                         "columnWidth": 1,
                         "itemId": "EmpDEptGrid",
                         "fieldLabel": "List",
                         "bindLevel": "empDEpt",
                         "bindable": "empDEpt",
                         "listeners": {
                              "select": "onEmpDEptGridItemClick"
                         },
                         "store": {
                              "fields": [],
                              "data": []
                         },
                         "columns": [{
                              "header": "deptId",
                              "text": "deptId",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "deptId",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "dept",
                              "text": "dept",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "dept",
                              "flex": 1
                         }, {
                              "header": "createdBy",
                              "text": "createdBy",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "createdBy",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "createdDate",
                              "text": "createdDate",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "createdDate",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "updatedBy",
                              "text": "updatedBy",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "updatedBy",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "updatedDate",
                              "text": "updatedDate",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "updatedDate",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "versionId",
                              "text": "versionId",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "versionId",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "activeStatus",
                              "text": "activeStatus",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "activeStatus",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "txnAccessCode",
                              "text": "txnAccessCode",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "txnAccessCode",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "xtype": "actioncolumn",
                              "customWidgetType": "vdActionColumn",
                              "width": 30,
                              "sortable": false,
                              "menuDisable": true,
                              "items": [{
                                   "icon": "images/delete.gif",
                                   "tooltip": "Delete Record",
                                   "handler": "onDeleteActionColumnClick"
                              }]
                         }]
                    }],
                    "customWidgetType": "vdCard"
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
               }, {
                    "xtype": "toolbar",
                    "customWidgetType": "vdTBar",
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "itemId": "cardPrev",
                         "text": "&laquo; Previous",
                         "handler": "showPreviousCard",
                         "disabled": true,
                         "margin": "0 5 0 5"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "itemId": "cardNext",
                         "text": "Next &raquo;",
                         "handler": "showNextCard",
                         "margin": "0 5 0 5"
                    }]
               }],
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});