var enterCoupletDetailsController = function($scope, $http){
    $scope.formInfo = {};
      $scope.saveData = function(){
      $http.post('/api/Couplet',$scope.formInfo).success(function(){
              alert("Data added");
           });
    };
 };
adminApp.controller('enterCoupletDetailsController',['$scope','$http',enterCoupletDetailsController]);
