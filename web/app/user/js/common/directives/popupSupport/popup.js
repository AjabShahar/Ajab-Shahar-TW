"use strict";

popupSupport.directive('popUp',["popupService",function (popupService) {
    return {
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: {
            width: '@', //width of the popup
            popupCount: '@',
            id: '@',//an id for the background overlay for manipulation via jquery
            overview:"="
        },
        templateUrl: '/user/js/common/templates/popupSupport/popup.html',
        link:function(scope,element){
                jQuery(element).css("height", jQuery(window).height());
        },
        controller: function ($scope) {

            $scope.getPopupCount = function () {
                var count = popupService.count();
                return new Array(count);
            };

            $scope.select = function (index) {
                popupService.select(index);
            };

            $scope.close = function () {
                popupService.deselect();
            };

            $scope.isActive = function (index) {
                return _.isEmpty(popupService.getSelected())? false : popupService.getSelected().index === index;
            };
        }
    };
}]);

