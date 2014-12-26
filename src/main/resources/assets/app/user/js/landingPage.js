(function($, AjabShahar, undefined) {
    AjabShahar.homePage = (function() {
    	function homePage(){

    		var _domReady = function(){
    		    _bindEvents();

    		    _initialiseScrolling();
                _setBgImage();
    		}

    		var _setBgImage = function() {
    		    var viewPortWidth = $(window).width(),
    		        bgImage = $('.px-layer3'),
    		        bgBodyImage = $('.px-layer1'),
    		        imageWidth = bgImage.width();

    		    bgImage.css({ 'left' : (viewPortWidth - imageWidth)/2});
    		    bgBodyImage.css({ 'left' : (viewPortWidth - imageWidth)/2 + 20});

    		    if (bgBodyImage <= 1140) {
    		        bgBodyImage.css({ 'left' : (viewPortWidth - imageWidth)/2});
    		    }
    		}

    		var _setStringAnimation = function() {
                var whiteString = $('.px-layer7');
                var xPos = $(window).scrollTop();


                if (xPos < 750 || (xPos >= 2500 && xPos < 4300)) {
                    whiteString.css('background-position', parseInt(-xPos / 50) + 'px ' + '0%');

                }
                else if ((xPos >= 750 && xPos < 2500) || (xPos >= 4300)) {
                    whiteString.css('background-position', parseInt(xPos / 50) + 'px ' + '0%');
                }

    		}

    		var _bindEvents = function() {
    		    $(window).on('resize', _.throttle(_setBgImage, 1000));
    		    $(window).on('scroll', _setStringAnimation);
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