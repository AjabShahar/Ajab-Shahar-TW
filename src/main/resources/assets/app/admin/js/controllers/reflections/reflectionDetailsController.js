var reflectionDetailsController = function($scope, $http,$window){
   $scope.formInfo = {};
   $scope.people = [];

   $scope.init = function(){
      $http.get('/api/people').success(function(data){
         $scope.people = data.people;
      });

   }

      $scope.saveData = function(){
        $http.post('/api/reflections',$scope.formInfo).success(function(data){
           $window.location.href = '/admin/home.html';
        });
      }
   $scope.init();
}

adminApp.controller('reflectionDetailsController',['$scope','$http','$window',reflectionDetailsController]);