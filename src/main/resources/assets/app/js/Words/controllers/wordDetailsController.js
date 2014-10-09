var wordDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/WordDetails',$scope.formInfo).success(function(){
          alert("Data added");
       });
  };
};

wordDetailsApp.controller('wordDetailsController',['$scope','$http',wordDetailsController]);
