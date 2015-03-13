(function($, AjabShahar, undefined) {
    AjabShahar.homePage = (function() {
    	function homePage(){

    		var _domReady = function(){
    		    _loadWindow();
    		    _initialiseScrolling();
    		}


    		var _initialiseScrolling = function() {
                $.stellar({
                    horizontalScrolling: false,
                    responsive: true,
                    scrollProperty: 'scroll',
                    positionProperty: 'transform',
                });
    		}

    		var _loadWindow = function() {
    		    $('#ajab_container').css('display', 'none');
                $(window).load(function() {
                  $('#ajab_container').css('display', 'block');
                  $("#loader_wrapper").css('display', 'none');
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
