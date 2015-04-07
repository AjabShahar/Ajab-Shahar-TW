'use strict';

commonApp.directive("errorMessage", function () {
    return {
        restrict: 'E',
        scope: {
            showError: '=',
            name: '@',
        },
        template: '<span class="alert alert-danger" role="alert" ng-show="showError"> {{name}} is required</span>'
    }
});
