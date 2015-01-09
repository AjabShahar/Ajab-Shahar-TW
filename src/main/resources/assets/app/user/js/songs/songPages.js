//(function($, AjabShahar, undefined) {
//
//    AjabShahar.songPages = (function() {
//        function songPages() {
//            var _domReady = function() {
//                _bindEvents();
////                _setLeftNav();
//            }
//
//            var _bindEvents = function() {
//
////                var that = this;
////                $(window).on("scroll", function() {
////                    _onScroll();
////                });
//
////                $("ul.slide-info").on('click', 'li>a', function() {
////                    var selector = $(this).data('slide'),
////                        refElement = $(this).closest('#song_container').find('#' + selector);
////                    refElement.slideToggle();
////                    refElement.siblings('.info').hide();
////                })
//
//
//            }
//
////            var _setLeftNav = function() {
////
////                var topMargin = $('.page-header').height() + $('.carousel-wrapper').height();
////                $("#left_nav").css({'top': topMargin});
////            }
////
////            var _onScroll = function(event) {
////                var scrollPos = $(document).scrollTop();
////                $('#left_nav a').each(function () {
////                    var currLink = $(this),
////                        refElement = $(currLink.attr("href")),
////                        refElementPos = refElement.offset();
////                    if ( (refElement.length != 0) && (scrollPos > refElementPos.top) && (scrollPos <= (refElementPos.top + refElement.height()) )) {
////                        $('#left_nav ul li a').removeClass("active");
////                        currLink.addClass("active");
////                    }
////                    else{
////                        currLink.removeClass("active");
////                    }
////                });
////            }
//
//
//
//            this.init = (function(){
//                $(function(){
//                    _domReady();
//                });
//
//            })();
//            return this;
//        }
//        return new songPages();
//    })();
//
//})(jQuery, window.AjabShahar = window.AjabShahar || {});

/**
 * Left Menu Slide Out
 */
 ;
 (function(window, document, $) {
 	"use strict";


 	$(document).ready(function() {

 		$(".filter").on("click", function() {
 			$(".nav-list").toggleClass("show");
 			$(".ajab-wrapper").toggleClass("left-menu-open");
 			return false;
 		});

}); // Close DOM ready.

 })(window, document, jQuery);