Brewer = Brewer || {};

Brewer.ClientSearchSpeed = (function() {
	
	function ClientSearchSpeed() {
		this.clientModalSearchSpeed = $('#searchSpeedClients');
		this.nameInput = $('#nameClientModal');
		this.searchSpeedBtn = $('.js-search-speed-clients-btn'); 
		this.containerTableSearch = $('#containerTableSearchSpeedClients');
		this.htmlTableSearch = $('#table-search-speed-client').html();
		this.template = Handlebars.compile(this.htmlTableSearch);
		this.messageError = $('.js-message-error');
	}
	
	ClientSearchSpeed.prototype.init = function() {
		this.searchSpeedBtn.on('click', onSearchSpeedClicked.bind(this));
	}
	
	function onSearchSpeedClicked(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.clientModalSearchSpeed.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				name: this.nameInput.val()
			}, 
			success: onSearchClicked.bind(this),
			error: onSearchError.bind(this)
		});
	}
	
	function onSearchClicked(result) {
		var html = this.template(result);
		this.containerTableSearch.html(html);
		this.messageError.addClass('hidden');
	} 
	
	function onSearchError() {
		this.messageError.removeClass('hidden');
	}
	
	return ClientSearchSpeed;
	
}());

$(function() {
	var clientSearchSpeed = new Brewer.ClientSearchSpeed();
	clientSearchSpeed.init();
});