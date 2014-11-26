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

            }

            var _toggleCarousel = function() {
                $(".carousel-box").slideToggle();
            }


            this.setLeftNav = function() {

                var topMargin = $('.page-header').height() + $('.carousel-wrapper').height();
                console.log(topMargin);
                $("#left_nav").css({'top': topMargin});
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