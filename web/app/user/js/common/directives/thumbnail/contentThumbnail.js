angular.module("thumbnailModule").directive("contentThumbnail",["$timeout",function($timeout){
    
    var controller = function($scope){
        $scope.shouldShowDetails = true;
        $scope.showDetails = function(){
            $scope.shouldShowDetails = true;
        };

        $scope.hideDetails = function(){
            if(!$scope.keepExpanded){
                $scope.shouldShowDetails = false;
            }
        };
        $scope.onTimeOut = function(){
            $scope.hideDetails();
        };

        if(!$scope.keepExpanded){
            $timeout($scope.onTimeOut,1000);
        }
    };
    
    return {
        restrict:"E",
        scope:{
            content:"=",
            currentIndex:"@",
            onClick:"=",
            format:"=",
            customStyle:"@",
            keepExpanded:"@"
        },
        templateUrl:"/user/js/common/directives/thumbnail/contentThumbnail.html" ,
        controller:controller
    }
}]);