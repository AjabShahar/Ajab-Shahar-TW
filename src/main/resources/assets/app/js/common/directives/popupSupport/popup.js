document.createElement('pop-up');
var popupSupport = angular.module('PopupSupport', [])
    .directive('popUp', function() {
        return {
            restrict: 'E',
            transclude: true,
            replace: true,
            scope: {
                show: '&', //true/false - displays popup
                onClose: '&', //close click handler ~ callback to controller
                closeButton: '@', //true-false ~ displays/hides close option
                width: '@', //width of the popup
                overlayId: '@'//an id for the background overlay for manipulation via jquery
            },
            templateUrl: '/js/common/templates/popupSupport/popup.html',
            controller: function($scope) {
                $scope.$watch(function() { return $scope.show(); }, function(newValue, oldValue) {
                    if (newValue != true)
                        return;

                    if ($scope.overlayId) {
                        jQuery('#' + $scope.overlayId).css("height", jQuery(window).height());
                    }
                });
            }
        };
    });    

var popUpController = function($scope){
    $scope.shouldBeOpen = false;

    $scope.open = function(){
        $scope.shouldBeOpen = true;
    }

    $scope.onClose = function(){
        $scope.shouldBeOpen = false;
    }
};
popupSupport.controller('popUpController',['$scope',popUpController]);