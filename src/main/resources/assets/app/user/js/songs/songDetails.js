(function($, AjabShahar, undefined) {

    AjabShahar.songDetails = (function() {
        function songDetails() {
            var _domReady = function() {
                _bindEvents();
            }

            var _bindEvents = function() {
                $(".headerArt a.menuTree").on("click", function() {
                  $(".headerWrapper").slideToggle();
                });
            }


            this.init = (function(){
                $(function(){
                    _domReady();

                });

            })();
            return this;
        }
        return new songDetails();
    })();

})(jQuery, window.AjabShahar = window.AjabShahar || {});