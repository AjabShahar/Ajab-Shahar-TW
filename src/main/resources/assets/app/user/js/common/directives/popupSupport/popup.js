var popUp = function() {
        return {
            restrict: 'E',
            transclude: true,
            replace: true,
            scope: {
                show: '&', //true/false - displays popup
                onClose: '&', //close click handler ~ callback to controller
                closeButton: '@', //true-false ~ displays/hides close option
                width: '@', //width of the popup
                id: '@'//an id for the background overlay for manipulation via jquery
            },
            templateUrl: '/user/js/common/templates/popupSupport/popup.html',
            controller: function($scope) {
                $scope.$watch(function() { return $scope.show(); }, function(newValue, oldValue) {
                    if (newValue != true)
                        return;

                    if ($scope.overlayId) {
                        jQuery('#' + $scope.overlayId).css("height", jQuery(window).height());
                    }
                });

                $scope.isClosed = function(){
                    return !$scope.show();
                }
            }
        };
    };

popupSupport.directive('popUp',popUp);