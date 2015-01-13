var popUp = function() {
        return {
            restrict: 'E',
            transclude: true,
            replace: true,
            scope: {
                show: '&', //true/false - displays popup
                width: '@', //width of the popup
                popupCount: '@',
                id: '@',//an id for the background overlay for manipulation via jquery
                index: '@',
                detailsService:'=',
            },
            templateUrl: '/user/js/common/templates/popupSupport/popup.html',
            controller: function($scope,$rootScope) {
                $scope.$watch(function() { return $scope.show(); }, function(newValue, oldValue) {
                    if (newValue != true)
                        return;

                    if ($scope.overlayId) {
                        jQuery('#' + $scope.overlayId).css("height", jQuery(window).height());
                    }
                });

                $scope.getPopupCount = function() {
                    return ($scope.popupCount==null)?new Array(15):new Array(parseInt($scope.popupCount));
                }

                $scope.onSelect = function(index){
                    $scope.detailsService.select(parseInt($scope.index),index);
                }

                $scope.onClose = function(index){
                    $scope.detailsService.onClose($scope.id);
                }

                $scope.isActive = function(index){
                    return $scope.index == index;
                }

                $scope.isClosed = function(){
                    return !$scope.show();
                }
            }
        };
    };

popupSupport.directive('popUp',popUp);