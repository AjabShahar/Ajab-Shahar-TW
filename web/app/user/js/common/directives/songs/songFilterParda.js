'use strict';

filterModule.directive("songFilterParda", function () {

    return {
        restrict: 'EA',
        templateUrl: '/user/js/common/templates/songs/songFilterParda.html',
        scope: {
            filterItems: "=",
            selectHandler: "=",
            showThisParda: "="
        },

        link: function (scope) {
            scope.itemClicked = function (item) {
                scope.selectHandler(item[0]);
            };
        }
    }
});