'use strict';

filterModule.directive("songFilterCategory", function () {

    return {
        restrict: 'EA',
        templateUrl: '/user/js/common/templates/songs/songFilterCategory.html',
        scope: {
            criteriaList: "=",
            selectHandler: "=",
            deselectHandler: "=",
            clearFilters: "="
        },

        link: function (scope) {
            scope.criteriaClicked = function (criteria) {
                scope.selectHandler(criteria);
            };

            scope.criteriaRemoved = function (criteria) {
                scope.deselectHandler(criteria);
            };

            scope.resetFilters = function () {
                scope.clearFilters();
            }
        }
    }
});