Brewer = Brewer || {};

Brewer.SelectionMulti = (function() {
	
	function SelectionMulti() {
		this.statusBtn = $('.js-status-btn');
		this.selectionCheckbox = $('.js-selection');
	}
	
	SelectionMulti.prototype.init = function() {
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
	}
	
	function onStatusBtnClicado(event) {
		var buttonClick = $(event.currentTarget);
		var status = buttonClick.data('status');
		
		var selectionCheckboxs = this.selectionCheckbox.filter(':checked');
		var codes = $.map(selectionCheckboxs, function (c) {
			return $(c).data('code');
		});
		
		if (codes.length > 0) {
			
			$.ajax({
				url: '/brewer/users/status',
				method: 'PUT',
				data: {
					codes: codes,
					status: status
				},
				success: function() {
					window.location.reload();
				}
			});
		}
		
	}
	
	return SelectionMulti;
	
}());

$(function() {
	var selectionMulti = new Brewer.SelectionMulti();
	selectionMulti.init();
});