Ext.define('Testproject.view.databrowsercalendar.DBCalendar', {
	extend : 'Testproject.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Testproject.view.databrowsercalendar.DBCalendarController',
	             'Testproject.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
