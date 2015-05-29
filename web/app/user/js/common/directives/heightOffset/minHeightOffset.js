"use strict";

angular.module('utilities').directive('minHeightOffset', function () {
    return {
        scope: {},
        link: function (scope, element, attrs) {
            var height = parseInt(attrs.heightOffset)+ $(window).height();

            $(element).css('min-height', height + 'px');
        }
    }
});
