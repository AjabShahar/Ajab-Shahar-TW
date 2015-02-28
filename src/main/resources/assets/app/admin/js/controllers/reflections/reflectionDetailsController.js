var reflectionDetailsController = function($scope, $http,$window, $location,reflectionContentService){
   $scope.formInfo = {"reflectionTranscripts":[],"speaker":{}};
   $scope.people = [];
   $scope.urlId = $location.search().id;

   $scope.init = function(){
      reflectionContentService.getPeople().success(function(data){
         $scope.people = data.people;
      });

      if($scope.urlId!=null && $scope.urlId!=''){
          reflectionContentService.getRefectionById($scope.urlId)
                             .success(function (data) {
                               $scope.formInfo = data;
                               $scope.formInfo.speaker = data.speaker;
          });
      }

   };

      $scope.saveData = function(){
       reflectionContentService.saveReflection($scope.formInfo).success(function(data){
           $window.location.href = '/admin/home.html';
        });
      };

      $scope.updateData = function(){
        reflectionContentService.updateReflection($scope.formInfo).success(function(data){
                   $window.location.href = '/admin/home.html';
        });

      };

   $scope.init();
};

reflectionsAdminApp.controller('reflectionDetailsController',['$scope','$http','$window','$location','reflectionContentService',reflectionDetailsController]);