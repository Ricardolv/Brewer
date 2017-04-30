var Brewer = Brewer || {};

Brewer.MaskCpfCnpf = (function () {
	
	function MaskCpfCnpf() {
		this.radioPersonType = $('.js-radio-personType');
		this.labelCpfCnpj = $('[for=cpfOrCnpj]');
		this.inputCpfCnpj = $('#cpfOrCnpj');
	}
	
	MaskCpfCnpf.prototype.start = function () {
		this.radioPersonType.on('change', onPersonTypeAlter.bind(this));
	}
	
	function onPersonTypeAlter(event) {
		var personTypeSelected = $(event.currentTarget);
		
		this.labelCpfCnpj.text(personTypeSelected.data('document'));
		this.inputCpfCnpj.mask(personTypeSelected.data('inputmask'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpf;
	
}());

$(function() {
	
	var maskCpfCnpf = new Brewer.MaskCpfCnpf();
	maskCpfCnpf.start();
	
});