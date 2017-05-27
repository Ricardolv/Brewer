Brewer.Sale = (function() {
	
	function Sale(tableItems) {
		this.tableItems = tableItems;
		
		this.totalValueBox = $('.js-total-value-box');
		this.freightValueInput = $('#freightValue');
		this.discountValueInput = $('#discountValue');
		this.totalValueBoxContainer = $('.js-total-value-box-container');
		
		this.freightValue = 0;
		this.totalItemsValue = 0;
		this.discountValue = 0;
	}
	
	Sale.prototype.init = function() {
		this.tableItems.on('table-items-updated', onTableItemsUpdated.bind(this));
		this.freightValueInput.on('keyup', onFreightValueChanged.bind(this));
		this.discountValueInput.on('keyup', onDiscountValueChanged.bind(this));
		
		this.tableItems.on('table-items-updated', onValueChanged.bind(this));
		this.freightValueInput.on('keyup', onValueChanged.bind(this));
		this.discountValueInput.on('keyup', onValueChanged.bind(this));
	}
	
	function onTableItemsUpdated(event, tableItemsValue) {
		this.totalItemsValue = tableItemsValue == null ? 0 : tableItemsValue;
	}
	
	function onFreightValueChanged(event) {
		this.freightValue = Brewer.recoverValue($(event.target).val());
	}
	
	function onDiscountValueChanged() {
		this.discountValue = Brewer.recoverValue($(event.target).val());
	}
	
	function onValueChanged() {
		var totalValue = this.totalItemsValue + this.freightValue - this.discountValue;
		this.totalValueBox.html(Brewer.currencyFormat(totalValue));
		
		this.totalValueBoxContainer.toggleClass('negative', totalValue < 0);
	}
	
	return Sale;
	
	
}());


$(function() {
	
	var autoComplete = new Brewer.Autocomplete();
	autoComplete.init();
	
	var tableItems = new Brewer.TableItems(autoComplete);
	tableItems.init();
	
	var sale = new Brewer.Sale(tableItems);
	sale.init();
	
});