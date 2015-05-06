angular.module("reflection").controller('reflectionDetailsController',['$scope','$route','reflectionsContentService',function($scope,$route,reflectionsContentService) {

    $scope.init = function () {
        $scope.reflectionId = $route.current.params.id;
        reflectionsContentService.getReflection($scope.reflectionId).success(function(reflectionDetails){
            $scope.reflectionDetails = new AjabShahar.DetailsObject(reflectionDetails,"reflection");
            $scope.reflectionDetails.type = "";
        });

    }();

    $scope.isInitialized = function () {
        return !_.isEmpty($scope.reflectionDetails );
    }
}]);