//var enterSplashScreenOptionsController = function($scope,contentService){
//   $scope.formInfo = {};
//   $scope.test = {'name': 'test123'};
//   $scope.saveData = function() {
//         alert("hello");
//   };
//
//   contentService.postData(data);
// };
//
//introductionApp.controller('enterSplashScreenOptionsController',['$scope','contentService',enterSplashScreenOptionsController]);
//
//var postData = function(data, $http){
//   $http.post('/SplashScreenOptions',data).success(function(){
//      alert("Data added");
//   });
// };

var enterSplashScreenOptionsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/SplashScreenOptions',$scope.formInfo).success(function(){
        alert("Data added");
     });
  };
};

introductionApp.controller('enterSplashScreenOptionsController',['$scope','$http',enterSplashScreenOptionsController]);
