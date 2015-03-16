(function($, AjabShahar, undefined) {
    AjabShahar.songsPage = (function() {
    	function songsPage(){

    		var _domReady = function(){
    		   _bindEvents();
    		}

    		var _bindEvents = function() {
    		    $(".filter-button").on("click", function() {
                    var windowHeight = $(document).height();
                    $('.slide-nav-list').height(windowHeight);
                    return false;
                });
    		}

            this.init = (function(){
    			$(function(){
    				_domReady();
    			});

    		})();

    		return this;
    	}

    	return new songsPage();

    })();
})(jQuery, window.AjabShahar = window.AjabShahar || {});
