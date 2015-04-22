(function ($, AjabShahar, undefined) {
    AjabShahar.songsPage = (function () {
        function songsPage() {

            var _domReady = function () {
                _bindEvents();
                _setScrollableRegionHeight();
            };

            var _bindEvents = function () {
                $(".filter-button").on("click", function () {
                    var windowHeight = $(document).height();
                    $('.slide-nav-list').height(windowHeight);
                    return false;
                });
            };

            var _setScrollableRegionHeight = function() {
                 $('.grid-wrapper').height($(window).height() - 150);
            }

            this.init = (function () {
                $(function () {
                    _domReady();
                });

            })();

            return this;
        }

        return new songsPage();

    })();
})(jQuery, window.AjabShahar = window.AjabShahar || {});
