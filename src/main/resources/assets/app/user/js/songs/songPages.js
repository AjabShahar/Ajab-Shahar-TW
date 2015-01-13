(function($, AjabShahar, undefined) {
    AjabShahar.songsPage = (function() {
    	function songsPage(){

    		var _domReady = function(){
    		   _bindEvents();

    		}

    		var _bindEvents = function() {
    		    $(".filter-button").on("click", function() {
                    $(".slide-nav-list").toggleClass("show");
                     _setLeftNavHeight();
                    return false;
                });
    		}

    		var _setLeftNavHeight = function() {
    		    var windowHeight = $(window).height();
    		    $('.slide-nav-list .filters').height(windowHeight);
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
