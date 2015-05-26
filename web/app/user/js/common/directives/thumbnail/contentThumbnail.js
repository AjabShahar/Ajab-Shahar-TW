angular.module("thumbnailModule").directive("contentThumbnail", ["$timeout",
    function($timeout) {

        var controller = function($scope) {
            $scope.bubbleShowClass = "thumbdetails";


            $scope.showDetails = function() {
                $scope.bubbleShowClass = "thumbdetails";
            };

            $scope.hideDetails = function() {
                if(!$scope.keepExpanded) {
                    $scope.bubbleShowClass = "thumbdetails collapse";
                }
            }    
            $scope.onTimeOut = function() {
                $scope.hideDetails();
            };
            if(!$scope.keepExpanded) { 
              $timeout($scope.onTimeOut);
            }
        };

        return {
            restrict: "E",
            scope: {
                content: "=",
                currentIndex: "@",
                onClick: "=",
                format: "=",
                customStyle: "@",
                keepExpanded: "@"
            },
            templateUrl: "/user/js/common/directives/thumbnail/contentThumbnail.html",
            controller: controller
        }
    }
]);