var enterSplashScreenOptionsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/SplashScreenOptions',$scope.formInfo).success(function(){
        alert("Data added");
     });
  };
};

introductionApp.controller('enterSplashScreenOptionsController',['$scope','$http',enterSplashScreenOptionsController]);
