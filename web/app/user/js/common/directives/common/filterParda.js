'use strict';

filterModule.directive("filterParda", function () {

    return {
        restrict: 'EA',
        templateUrl: '/user/js/common/templates/common/filterParda.html',
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