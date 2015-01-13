(function($, AjabShahar, undefined) {
    AjabShahar.homePage = (function() {
    	function homePage(){

    		var _domReady = function(){
    		    _initialiseScrolling();
    		}


    		var _initialiseScrolling = function() {
                $.stellar({
                    horizontalScrolling: false,
                    responsive: true
                });
    		}

            this.init = (function(){
    			$(function(){
    				_domReady();

    			});

    		})();

    		return this;
    	}

    	return new homePage();

    })();
})(jQuery, window.AjabShahar = window.AjabShahar || {});