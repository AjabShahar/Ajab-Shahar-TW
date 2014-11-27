(function($, AjabShahar, undefined) {

    AjabShahar.songDetails = (function() {
        function songDetails() {
            var _domReady = function() {
                _bindEvents();
                this.setLeftNav();
            }

            var _bindEvents = function() {

                var that = this;
                $(".headerArt a.menuTree").on("click", function() {
                  $(".headerWrapper").slideToggle();
                });
                $(".carousel-wrapper").on("click", ".see-more .version", function() {
                  _toggleCarousel();
                });
                $(window).on("scroll", function() {
                    _onScroll();
                });

                $('#left_nav a[href^="#"]').on('click', function (e) {
                    e.preventDefault();
                    var that = this;
                    $(document).off("scroll");

                    $('a').each(function () {
                        $(this).removeClass('active');
                    })
                    $(this).addClass('active');

                    var target = this.hash,
                        menu = target;
                    $target = $(target);
                    $('html, body').stop().animate({
                        'scrollTop': $target.offset().top+2
                    }, 500, 'swing', function () {
                        window.location.hash = target;
                        $(document).on("scroll", _onScroll);
                    });
                });

            }

            var _toggleCarousel = function() {
                $(".carousel-box").slideToggle();
                $(".carousel-wrapper.jcarousel-wrapper .see-more .arrow").toggleClass("up-arrow");
            }


            this.setLeftNav = function() {

                var topMargin = $('.page-header').height() + $('.carousel-wrapper').height();
                $("#left_nav").css({'top': topMargin});
            }

            var _onScroll = function(event) {
                var scrollPos = $(document).scrollTop();
                $('#left_nav a').each(function () {
                    var currLink = $(this);
                    var refElement = $(currLink.attr("href"));
                    if (refElement.position().top <= scrollPos && refElement.position().top + refElement.height() > scrollPos) {
                        $('#left_nav ul li a').removeClass("active");
                        currLink.addClass("active");
                    }
                    else{
                        currLink.removeClass("active");
                    }
                });
            }



            this.init = (function(){
                $(function(){
                    _domReady();
                    $('.jcarousel').jcarousel();

                });

            })();
            return this;
        }
        return new songDetails();
    })();

})(jQuery, window.AjabShahar = window.AjabShahar || {});