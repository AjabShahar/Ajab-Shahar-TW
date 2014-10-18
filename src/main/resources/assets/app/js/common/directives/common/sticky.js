angular.module('sticky', [])
.directive('sticky', [ function () {
    return {
        restrict: 'A',

        link: function ($scope, $elem, $attrs) {
            var offsetTop = 0,
                $window = angular.element(window),
                initialPositionStyle = $elem.css('position'),
                stickyLine,
                scrollTop;

            // Set the top offset
            $elem.css('top', '0');
            $window.on('scroll', checkSticky);
            setInitial();

            function setInitial() {
                stickyLine = $elem[0].offsetTop;
                checkSticky();
            }

            function checkSticky() {
                scrollTop = window.pageYOffset;
                if (scrollTop >= stickyLine) {
                    $elem.css('position', 'fixed');
                    $elem.addClass('fixed');
                } else {
                    $elem.removeClass('fixed');
                    $elem.css('position', initialPositionStyle);
                }
            }
        }
    };
}]);