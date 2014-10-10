var enterWordDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/Word',$scope.formInfo).success(function(){
          alert("Data added");
       });
  };
};

adminApp.controller('enterWordDetailsController',['$scope','$http',enterWordDetailsController]);
