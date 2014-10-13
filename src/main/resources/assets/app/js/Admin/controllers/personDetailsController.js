var personDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/Person',$scope.formInfo).success(function(){
          alert("Data added");
       });
  };
};

adminApp.controller('personDetailsController',['$scope','$http',personDetailsController]);
