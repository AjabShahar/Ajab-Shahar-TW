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
