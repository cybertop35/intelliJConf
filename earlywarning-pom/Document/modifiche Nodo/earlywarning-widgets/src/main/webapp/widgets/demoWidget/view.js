define(["jquery", "underscore", "backbone", "app"], function($, _, Backbone, app) {
	var module = {};

	module.DemoWidgetView = Backbone.View.extend({
		render: function() {
			return this;
		}
	});
		
	return module;
});
