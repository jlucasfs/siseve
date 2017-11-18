//var $ = $Query.noConflict();
$.mask.masks = $.extend($.mask.masks, {
	diaMesAnoMask:     	{ mask: '99/99/9999' },
	anoMesMask:     	{ mask: '99/9999' },
	horaMinutoMask:	    { mask: '99:99' },
	isbnMask10:	   	 	{ mask: '9-999999-99-9' },
	isbnMask13:	   	 	{ mask: '999-9-99-999999-9' },
	paginaMask:	    	{ mask: '99-99' }
});
/**
 * Método utilizado para inserir máscara nos filtro de um datatable $SF
 */
$.fn.mascara = function(){
	var input_competencia = $(this).parent().next();
	$(input_competencia).attr('alt',$(this).attr('class'));
	$(input_competencia).setMask();
};
$.fn.mascaraTxt = function(param){		
	$(this).attr('alt',param);	
	$(this).setMask();
};
