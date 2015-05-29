"use strict";

angular.module('utilities').directive('readMore', ['$timeout', function ($timeout) {
    return {
        scope: {},
        link: function (scope, element, attrs) {
            $timeout(function () {
                $(element).readmore({
                    moreLink: '<a href="#" class="read-link" >read more</a>',
                    lessLink: '<a href="#" class="read-link" >read less</a>'

                });
            });
        }
    }
}]);
