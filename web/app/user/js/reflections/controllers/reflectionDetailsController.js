angular.module("reflection").controller('reflectionDetailsController',['$scope','$route','reflectionsContentService','$rootScope',function($scope,$route,reflectionsContentService,$rootScope) {


    $scope.init = function () {
        $scope.reflectionId = $route.current.params.id;
        reflectionsContentService.getReflection($scope.reflectionId).success(function(reflectionDetails){
            $scope.reflectionDetails = new AjabShahar.DetailsObject(reflectionDetails,"reflection");
            $scope.reflectionDetails.type = "";

            $rootScope.pageSynopsis = {
                title:$scope.reflectionDetails.title,
                image:$scope.reflectionDetails.originalObject.thumbnailURL,
                description:$scope.reflectionDetails.originalObject.reflectionExcerpt
            };
        });

    }();

    $scope.isInitialized = function () {
        return !_.isEmpty($scope.reflectionDetails );
    }
}]);