(function($, AjabShahar, undefined) {
    AjabShahar.songsPage = (function() {
    	function songsPage(){

    		var _domReady = function(){
    		   _bindEvents();
    		}

    		var _bindEvents = function() {
    		    $(".filter-button").on("click", function() {
                    _slideLeftNav($(this));
                    return false;
                });
    		}

    		var _slideLeftNav = function(element) {
    		    $(".slide-nav-list").toggleClass("show");
    		    element.find(".collapse").toggleClass("expand");
    		    _setLeftNavHeight();

    		}

    		var _setLeftNavHeight = function() {
    		    var windowHeight = $(document).height();
    		    $('.slide-nav-list').height(windowHeight);
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
