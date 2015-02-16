angular.module("mainLanding").directive("contentThumbnail",["$timeout",function($timeout){
    
    var controller = function($scope){
        $scope.format = "transliteration";
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

        $scope.open = function(){
            $scope.content.showOverlay = true;
        };
        
        $scope.close = function(){
            $scope.content.showOverlay = false;
        };
        
        $timeout($scope.onTimeOut,1000);
    };
    
    return {
        restrict:"E",
        scope:{
            content:"=",
            format:"=",
            currentIndex:"@",
            totalCount:"@"
        },
        templateUrl:"/mainlanding/common/contentThumbnail.html" ,
        controller:controller
    }
}]);