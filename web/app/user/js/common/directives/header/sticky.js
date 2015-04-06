headerModule.directive('sticky', [ function () {
    return {
        restrict: 'A',
        scope: {
            onScroll: "&onScroll",
            onTop: "&onTop",
        },
        link: function ($scope, $elem, $attrs) {
            var offsetTop = 0,
            $window = angular.element(window),
            initialPositionStyle = $elem.css('position'),
            stickyLine,
            scrollTop;

            // Set the top offset
            $elem.css('top', '0');
            $window.on('scroll', checkSticky);
            angular.element(document).ready(setInitial);

            function setInitial() {
                stickyLine = $elem[0].offsetTop;
                checkSticky();
            }

            function checkSticky() {
                scrollTop = window.pageYOffset;
                if(scrollTop <= stickyLine) {
                    $elem.removeClass('fixed');
                    $elem.css('position', initialPositionStyle);
                    $scope.onTop();
                    return;
                }

                if (scrollTop > stickyLine) {
                    $elem.css('position', 'fixed');
                    $elem.addClass('fixed');
                    $scope.onScroll();

                }
            }
        }
    };
}]);
