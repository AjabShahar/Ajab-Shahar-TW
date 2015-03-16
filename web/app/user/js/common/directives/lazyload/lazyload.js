'use strict';

lazyLoadModule.directive("asLazyLoad", function() {
    return {
        restrict: 'A',
        scope: {},
        link: function ($scope, $elem, $attrs) {
            $elem.lazyload();
        }
    }
});
