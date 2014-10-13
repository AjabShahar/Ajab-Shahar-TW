var titleBasicDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/Title',$scope.formInfo).success(function(){
          alert("Data added");
       });
  };
};

adminApp.controller('titleBasicDetailsController',['$scope','$http',titleBasicDetailsController]);
