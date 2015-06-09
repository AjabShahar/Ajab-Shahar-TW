'use strict';

filterModule.directive("filterCategory", function () {

    return {
        restrict: 'EA',
        templateUrl: '/user/js/common/templates/common/filterCategory.html',
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