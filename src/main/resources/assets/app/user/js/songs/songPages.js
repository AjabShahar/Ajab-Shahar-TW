(function($, AjabShahar, undefined) {

    AjabShahar.songPages = (function() {
        function songPages() {
            var _domReady = function() {
                _bindEvents();
//                _setLeftNav();
            }

            var _bindEvents = function() {

                var that = this;
                $(".carousel-wrapper").on("click", ".see-more .version", function() {
                  _toggleCarousel();
                });
//                $(window).on("scroll", function() {
//                    _onScroll();
//                });

                $("ul.slide-info").on('click', 'li>a', function() {
                    var selector = $(this).data('slide'),
                        refElement = $(this).closest('#song_container').find('#' + selector);
                    refElement.slideToggle();
                    refElement.siblings('.info').hide();
                })


            }

            var _toggleCarousel = function() {
                $(".carousel-box").slideToggle();
                $(".carousel-wrapper.jcarousel-wrapper .see-more .arrow").toggleClass("up-arrow");
            }


//            var _setLeftNav = function() {
//
//                var topMargin = $('.page-header').height() + $('.carousel-wrapper').height();
//                $("#left_nav").css({'top': topMargin});
//            }
//
//            var _onScroll = function(event) {
//                var scrollPos = $(document).scrollTop();
//                $('#left_nav a').each(function () {
//                    var currLink = $(this),
//                        refElement = $(currLink.attr("href")),
//                        refElementPos = refElement.offset();
//                    if ( (refElement.length != 0) && (scrollPos > refElementPos.top) && (scrollPos <= (refElementPos.top + refElement.height()) )) {
//                        $('#left_nav ul li a').removeClass("active");
//                        currLink.addClass("active");
//                    }
//                    else{
//                        currLink.removeClass("active");
//                    }
//                });
//            }



            this.init = (function(){
                $(function(){
                    _domReady();
                    $('.jcarousel').jcarousel();
                });

            })();
            return this;
        }
        return new songPages();
    })();

})(jQuery, window.AjabShahar = window.AjabShahar || {});