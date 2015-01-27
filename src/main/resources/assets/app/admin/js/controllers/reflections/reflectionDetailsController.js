var reflectionDetailsController = function($scope, $http){
   $scope.formInfo = {};
   $scope.people = [];

   $scope.init = function(){
      $http.get('/api/people').success(function(data){
         $scope.people = data.people;
      });

   }

   $scope.init();
}

adminApp.controller('reflectionDetailsController',['$scope','$http',reflectionDetailsController]);