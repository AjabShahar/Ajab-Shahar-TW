(function($, AjabShahar, undefined) {
    AjabShahar.splashScreen = (function() {
    	function splashScreen(){

    		var _domReady = function() {
    	        _setScreenHeight();
    		}

    		var _setScreenHeight =  function() {
    		    var splashScreen = $('.splash_screen');
    		    if (splashScreen.length != 0) {
    		        splashScreen.height($(window).height());
    		    }
    		}

            this.init = (function(){
    			$(function(){
    				_domReady();

    			});

    		})();

    		return this;
    	}

    	return new splashScreen();

    })();
})(jQuery, window.AjabShahar = window.AjabShahar || {});