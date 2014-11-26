(function($, AjabShahar, undefined) {

    AjabShahar.songDetails = (function() {
        function songDetails() {
            var _domReady = function() {
                _bindEvents();
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