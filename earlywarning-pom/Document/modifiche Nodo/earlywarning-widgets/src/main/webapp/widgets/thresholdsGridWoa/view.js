require.config({
	paths: {
		tooltips: "../widgets/assets/tooltips",
		events: "../widgets/assets/widgets-events"
	}
});

define(["jquery", "underscore", "backbone", "app", "tooltips", "events"], function($, _, Backbone, app, tooltips, events) {
	var module = {};

	module.ThresholdsGridWoaView = Backbone.View.extend({
		className : 'thresholdsGridWoa-container',

		initialize: function() {
			this.model.value.errors = 0;
		},

		render: function() {
			this.$el.html(module.template(this.model));
			var maxThIndex = this.model.value.thresholds[0].index + '_' + this.model.widgetId;
			var minThIndex = this.model.value.thresholds[this.model.value.thresholds.length - 1].index + '_' + this.model.widgetId;
			var maxInput= this.$('#' + maxThIndex);
			var minInput= this.$('#' + minThIndex);
			if (parseFloat(minInput.find('.fromValue').val()) > parseFloat(this.model.value.minValue)){
				var warningMessage1 = this.model.value.minValueMessage;
				this.model.value.warnings++;
				minInput.find('.fromValue').addClass('input-warning-style');
				this.addTooltip(warningMessage1, minInput.find('.fromValue'), 'warning');
			}
			if (parseFloat(maxInput.find('.toValue').val()) < parseFloat(this.model.value.maxValue)) {
				var warningMessage2 = this.model.value.maxValueMessage;
				this.model.value.warnings++;
				maxInput.find('.toValue').addClass('input-warning-style');
				this.addTooltip(warningMessage2, maxInput.find('.toValue'), 'warning');
			}

			return this;
		},

		events: {
			'input .inputVal': 'onInput',
			'blur .inputVal': 'onBlur',
			'keypress .inputVal': 'onKeypress',
			'dblclick .thresholdBox': 'onDoubleClick',
			'click .checkbox-squareMin': 'onCheckboxSelectMin',
			'click .checkbox-squareMax': 'onCheckboxSelectMax',
			'click .add': 'addThreshold',
			'click .remove': 'removeThreshold'
		},

		onBlur: function(e) {
			if (this.enterKeyPressed) {
				this.enterKeyPressed = false;
			}
			else {
				this.onValueEvent(e);
			}
		},
		
		onCheckboxSelectMin : function(e){
			var currentEle = $(e.currentTarget);
			var index = parseInt($(currentEle).parent().parent().attr('data-index'));
			var thresholds = this.model.value.thresholds;
			console.log(thresholds);
			console.log($('.checkbox-square:checked').val());
			
				if(thresholds[index].EqualsMin){
				thresholds[index].EqualsMin = false;
				} else {
					thresholds[index].EqualsMin = true;
				}

		},
		
		onCheckboxSelectMax : function(e){
			var currentEle = $(e.currentTarget);
			var index = parseInt($(currentEle).parent().parent().attr('data-index'));
			var thresholds = this.model.value.thresholds;
			console.log(thresholds);
			console.log($('.checkbox-square:checked').val());
			
				if(thresholds[index].EqualsMax){
				thresholds[index].EqualsMax = false;
				} else {
					thresholds[index].EqualsMax = true;
				}

		},

		onInput: function(e) {
			this.onValueEvent(e);
		},

		onKeypress: function(e) {
			if (e.keyCode == 13 || e.which == 13) {
				this.onValueEvent(e);
				this.enterKeyPressed = true;
				$(e.currentTarget).blur();
			}
		},

		onDoubleClick: function(e){
		if ($(e.currentTarget).find('.thresholdInputBox').length == 0){
				e.stopPropagation();
				var currentEle = $(e.currentTarget);
				var value = $(e.currentTarget).html();
				this.updateVal(currentEle, value);
			}
		},

		updateVal : function(currentEle, value) {
			$(currentEle).html('<input class="inputVal thresholdInputBox" value="' + value.trim() + '" />');
			$(".thresholdInputBox").focus();
			var index = parseInt($(currentEle).parent().parent().attr('data-index'));
			var thresholds = this.model.value.thresholds;
			$(".thresholdInputBox").keyup(function (event) {
				if (event.keyCode == 13) {
					thresholds[index].score = $(".thresholdInputBox").val().trim();
					$(currentEle).html($(".thresholdInputBox").val().trim());
				}
				
			});
					
			var self = this;
			$(".thresholdInputBox").focusout(function () {
				if(self.errorMessage == undefined){
					thresholds[index].score = $(".thresholdInputBox").val().trim();
					$(currentEle).html($(".thresholdInputBox").val().trim());
				 }
			});
		},

		onValueEvent: function(e) {
			this.detectErrors(e);
			this.detectWarnings(e);
			if (this.errorMessage) {
				this.hideWarning(e);
				this.showError(e);
			} else {
				var inputValue = parseFloat($(e.currentTarget).val());
				this.hideError(e);
				if (this.warningMessage) {
					this.showWarning(e);
				} else {
					this.hideWarning(e);
				}
				var thresholds = this.model.value.thresholds;
				var currentInputIndex = $(e.currentTarget).parent().parent().attr('data-index');
				var previousInputIndex = $(e.currentTarget).parent().parent().prev().attr('data-index');
				if ($(e.currentTarget).hasClass('toValue')) {
					thresholds[currentInputIndex].to = inputValue;
					if(currentInputIndex != 0){
						thresholds[previousInputIndex].from = inputValue;
						$(e.currentTarget).parent().parent().prev().find('.fromValue').val(inputValue);
						$(e.currentTarget).parent().parent().prev().find('.toValue').blur();
					}
				} else {
					thresholds[currentInputIndex].from = inputValue;
					$(e.currentTarget).parent().parent().find('.toValue').blur();
				}
			}
		},

		isRequiredInputEmpty: function(e){
			return !$(e.currentTarget).val();
		},

		isInputInvalidNumber: function(e){
			return $(e.currentTarget).val() && !jQuery.isNumeric($(e.currentTarget).val())
		},

		isMaxLengthExceeded: function(e) {
			return this.model.value.maxLength && $(e.currentTarget).val().length > this.model.value.maxLength;
		},

		isFromGreaterThanTo: function(e) {
			var isError = false;
			if (parseInt($(e.currentTarget).parent().parent().attr('data-index')) != this.model.value.thresholds.length - 1 || !$(e.currentTarget).hasClass('fromValue')){
				var inputFromValue = $(e.currentTarget).parent().parent().find('.fromValue').val();
				var inputToValue = $(e.currentTarget).parent().parent().find('.toValue').val();
				if (parseFloat(inputFromValue) > parseFloat(inputToValue)){
					isError = true;
				}
			}

			return isError;
		},

		detectErrors: function(e) {
			this.required = false;
			var inputFromValue = $(e.currentTarget).parent().parent().find('.fromValue').val();
			var inputToValue = $(e.currentTarget).parent().parent().find('.toValue').val();
			if (this.isRequiredInputEmpty(e)) {
				this.addAndSetErrorMessage('Required field');
				this.required = true;
			} else if (this.isInputInvalidNumber(e)) {
				this.addAndSetErrorMessage('Invalid number');
			} else if (this.isMaxLengthExceeded(e)) {
				this.addAndSetErrorMessage('Max length ' + this.model.value.maxLength);
			} else if (this.isFromGreaterThanTo(e)) {
				this.addAndSetErrorMessage(inputFromValue + ' greater than ' + inputToValue);
			} else {
				if (this.errorMessage) {
					this.errorMessage = undefined;
					// this.hideError(e);
				}
			}
		},

		addAndSetErrorMessage: function(errorMessage) {
			this.errorMessage = errorMessage;
			this.model.value.errors++;
		},

		showError: function (e) {
			$(e.currentTarget).addClass('ux-input-error-style');
			this.addTooltip(this.errorMessage,$(e.currentTarget), 'error');
		},

		hideError: function(e){
			var minThIndex = this.model.value.thresholds[this.model.value.thresholds.length - 1].index + '_' + this.model.widgetId;
			if ($(e.currentTarget).parent().parent().is('#'+ minThIndex) && this.lowerTH == false && this.required == false){
				$(e.currentTarget).parent().parent().find('.toValue').removeClass('ux-input-error-style');
			}

			$(e.currentTarget).removeClass('ux-input-error-style');
			tooltips.destroyTooltip($(e.currentTarget));

			if(this.model.value.errors > 0){
				this.model.value.errors--;
			}
		},

		minThresholdsGreaterThanMinModelValue: function(e){
			var isWarning = false;
			var lowerThIndex = this.model.value.thresholds[this.model.value.thresholds.length - 1].index + '_' + this.model.widgetId;
			if ( $(e.currentTarget).parent().parent().is('#' + lowerThIndex) ){
				if ($(e.currentTarget).hasClass('fromValue')){
					if(parseFloat($(e.currentTarget).val()) >  this.model.value.minValue){
						isWarning = true;
					}
				}
			}

			return isWarning;
		},

		maxThresholdsLowerThanMaxModelValue: function(e){
			var isWarning = false;
			var greaterThIndex = this.model.value.thresholds[0].index + '_' + this.model.widgetId;
			if ( $(e.currentTarget).parent().parent().is('#' + greaterThIndex) ){
				if ($(e.currentTarget).hasClass('toValue')){
					if(parseFloat($(e.currentTarget).val()) <  this.model.value.maxValue){
						isWarning = true;
					}
				}
			}

			return isWarning;

		},

		detectWarnings: function(e) {
			if (this.minThresholdsGreaterThanMinModelValue(e)) {
				this.addAndSetWarningMessage(this.model.value.minValueMessage);
			} else if (this.maxThresholdsLowerThanMaxModelValue(e)){
				this.addAndSetWarningMessage(this.model.value.maxValueMessage);
			} else {
				if (this.warningMessage) {
					this.warningMessage = undefined;
					this.hideWarning(e);
				}
			}
		},

		addAndSetWarningMessage: function(warningMessage) {
			this.warningMessage = warningMessage;
		},

		showWarning: function (e) {
			$(e.currentTarget).addClass('input-warning-style');
			this.addTooltip(this.warningMessage,$(e.currentTarget), 'warning');
		},

		hideWarning: function(e){
			$(e.currentTarget).removeClass('input-warning-style');
			tooltips.destroyTooltip($(e.currentTarget));
		},

		addThreshold: function(e){
			if(this.model.value.thresholds.length < this.model.value.maxThresholdsNumber){
				var row = $(e.target).closest(".thRow");
				var index = parseInt(row.attr('data-index'));
				var fromVal = parseFloat(row.find('.toValue').val());
				if(this.model.value.thresholds.length > 1){
					row.before('<div id="'+ (index) + '_' + this.model.widgetId + '" class="row align-center thRow" data-index="' + (index) +'"><div class="col-lg-1 col-md-1 col-sm-1 s-right-space m-bottom-space"><input type="checkbox" value="' + valEqualMin +'"/></div><div class="col-lg-2 col-md-2 col-sm-2 s-right-space m-bottom-space"><input value="' + fromVal + '" maxlength class="fromValue inputVal" disabled /></div><div class="col-lg-1 col-md-1 col-sm-1 s-right-space m-bottom-space"><input type="checkbox" value="' + valEqualMax + '"/></div><div class="col-lg-2 col-md-2 col-sm-2 s-right-space m-bottom-space"><input value="" maxlength class="toValue inputVal ux-input-error-style" /></div><div class="col-lg-4 col-md-4 col-sm-4 s-right-space m-bottom-space"><div class="thresholdBox text-center ux-text-grey-p1 ux-text-oneline s-right-space s-left-space ux-background-grey-m3">'+this.model.value.defaultThresholdName+'</div></div><div class="col-lg-1 col-md-1 col-sm-1 s-left-space m-bottom-space"><span class="add glyphicon glyphicon-plus-sign ux-text-grey ux-cursor-click" aria-hidden="true"></span></div><div class="col-lg-1 col-md-1 col-sm-1 m-bottom-space"><span class="remove glyphicon glyphicon-minus-sign ux-text-grey ux-cursor-click" aria-hidden="true"></span></div></div>');
				} else if(this.model.value.thresholds.length == 1){
					row.before('<div id="'+ (index) + '_' + this.model.widgetId + '" class="row align-center thRow" data-index="' + (index) + '"><div class="col-lg-1 col-md-1 col-sm-1 s-right-space m-bottom-space"><input type="checkbox" value="' + valEqualMin + '"/></div><div class="col-lg-2 col-md-2 col-sm-2 s-right-space m-bottom-space"><input value="' + fromVal + '" maxlength class="fromValue inputVal" disabled /></div><div class="col-lg-1 col-md-1 col-sm-1 s-right-space m-bottom-space"><input type="checkbox" value="' + valEqualMax + '"/></div><div class="col-lg-2 col-md-2 col-sm-2 s-right-space m-bottom-space"><input value="" maxlength class="toValue inputVal ux-input-error-style" /></div><div class="col-lg-4 col-md-4 col-sm-4 s-right-space m-bottom-space"><div class="thresholdBox text-center ux-text-grey-p1 ux-text-oneline s-right-space s-left-space ux-background-grey-m3" >'+this.model.value.defaultThresholdName+'</div></div><div class="col-lg-1 col-md-1 col-sm-1 s-left-space m-bottom-space"><span class="remove glyphicon glyphicon-minus-sign ux-text-grey ux-cursor-click" aria-hidden="true"></span></div></div>');
				}
				this.model.value.thresholds.splice(index, 0, {EqualsMin: valEqualMin, from: fromVal, EqualsMax: valEqualMax, to: null, score: this.model.value.defaultThresholdName, index: index, labelColor: 'grey-m3'});
				this.model.value.errors++;

				var firstVal = index + 1;
				for(i = firstVal; i < this.model.value.thresholds.length; i = i + 1){
					this.model.value.thresholds[i].index = i;
				}
				row.attr('id', (index+1) + '_' + this.model.widgetId);
				row.attr('data-index', (index+1).toString());
				var nextEl = row.nextAll();
				for(j = 0; j < nextEl.length; j = j + 1){
					$(nextEl[j]).attr('id', (index + 2 + j) + '_' + this.model.widgetId);
					$(nextEl[j]).attr('data-index', (index + 2 + j).toString());
				}
			} else {
				$(e.target).parent().parent().parent().find('.add').parent().remove();
			}
		},

		removeThreshold: function(e){
			// Cannot delete last row
			if (this.model.value.thresholds.length == 1) {
				return;
			}
			
			if($(e.target).parent().parent().parent().find('.add').length == 0){
				if($(e.target).parent().parent().parent().find('.remove').length < this.model.value.maxThresholdsNumber){
					$(e.target).parent().parent().parent().find('.remove').parent().before('<div class="col-lg-1 col-md-1 col-sm-1 s-left-space s-right-space m-bottom-space"><span class="add glyphicon glyphicon-plus-sign ux-text-grey ux-cursor-click" aria-hidden="true"></span></div>');
					$(e.target).parent().parent().parent().find('.remove').last().parent().parent().next().append('<div class="col-lg-1 col-md-1 col-sm-1 s-left-space s-right-space m-bottom-space"><span class="add glyphicon glyphicon-plus-sign ux-text-grey ux-cursor-click" aria-hidden="true"></span></div>');
					$(e.target).parent().parent().parent().find('.remove').first().parent().parent().find('.add').parent().remove();
				}
			}

			var index = parseInt($(e.currentTarget).parent().parent().attr('data-index'));
			if (index == 0 && this.model.value.thresholds.length > 2 ){
				$(e.currentTarget).parent().parent().next().find('.add').parent().remove();
			}

			if($(e.currentTarget).parent().parent().find('.toValue').hasClass('ux-input-error-style')){
				this.model.value.errors--;
			}

			var row = $(e.target).closest(".thRow");

			// remove from model and from dom
			var rowIndex = row.index();
			this.model.value.thresholds.splice(rowIndex, 1);

			for(i = rowIndex; i < this.model.value.thresholds.length; i = i + 1){
				this.model.value.thresholds[i].index = i;
			}

			var fromVal = parseFloat(row.next().find('.toValue').val());
			row.prev().find('.fromValue').val(fromVal);
			var rowPrevId = row.prev().attr('id');
			var thresholds = this.model.value.thresholds;
			for (i = 0; i < thresholds.length; i++) {
				if (thresholds[i].index + '_' + this.model.widgetId == rowPrevId){
					thresholds[i].from = fromVal;
					break;
				}
			}

			var nextEl = row.nextAll();

			row.remove();

			for(j = 0; j < nextEl.length; j = j + 1){
				$(nextEl[j]).attr('id', (rowIndex + j) + '_' + this.model.widgetId);
				$(nextEl[j]).attr('data-index', (rowIndex + j).toString());
			}
		},

		addTooltip: function(tooltip, rowEl, tooltipType) {
			var content = '<div class="s-space ux-tooltip-title">';
			content+='<div class="ux-body1">'+tooltip+'</div>';
			content += '</div>';
			var borderColor;
			if (tooltipType == 'error'){
				borderColor = 'red';
			} else {
				borderColor = 'orange';
			}
			tooltips.createInfoTooltip(rowEl, content, borderColor);
			this.hideOnScrolling();
		},

		hideOnScrolling: function() {
			var self = this;
			app.event_bus.on('ux-scrolling', function(e) {
				self.$el.qtip('hide', true);
			});
		},

		dispose: function() {
			tooltips.destroyTooltip(this.$el);
		}
	});

	return module;
});