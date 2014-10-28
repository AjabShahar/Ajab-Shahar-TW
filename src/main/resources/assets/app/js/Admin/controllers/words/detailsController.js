var wordDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/words',$scope.formInfo).success(function(){
          alert("Data added");
       });
  };
};

adminApp.controller('wordDetailsController',['$scope','$http',wordDetailsController]);
