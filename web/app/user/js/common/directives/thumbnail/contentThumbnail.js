angular.module("thumbnailModule").directive("contentThumbnail",["$timeout",function($timeout){
    
    var controller = function($scope){
        $scope.shouldShowDetails = true;
        $scope.showDetails = function(){
            $scope.shouldShowDetails = true;
        };

        $scope.hideDetails = function(){
            $scope.shouldShowDetails = false;
        };
        $scope.onTimeOut = function(){
            $scope.hideDetails();
        };

        $timeout($scope.onTimeOut,1000);
    };
    
    return {
        restrict:"E",
        scope:{
            content:"=",
            currentIndex:"@",
            onClick:"=",
            format:"=",
            customStyle:"@"
        },
        templateUrl:"/user/js/common/directives/thumbnail/contentThumbnail.html" ,
        controller:controller
    }
}]);