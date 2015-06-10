"use strict";

angular.module('utilities').directive('heightOffset', function () {
    return {
        scope: {},
        link: function (scope, element, attrs) {
            var offset = parseInt(attrs.heightOffset);
            $(element).height($(window).height() + offset);
        }
    }
});


angular.module('utilities').directive('heightFactor', function ($timeout) {
    return function (scope, element, attrs) {

            $timeout(init, false);
            function init() {
                var offset = parseInt(attrs.heightFactor);
                var deltaFactor = offset - ($(element).height() % offset);
                $(element).height($(element).height() + deltaFactor);
            }
        };
    });


