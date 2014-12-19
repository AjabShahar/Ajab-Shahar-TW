var personDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {};
  $scope.saveData = function(){
  $http.post('/api/people',$scope.formInfo).success(function(){
          alert("Data added");
          $window.location.href = '/admin/partials/person/details.html';
       });
  };
};

adminApp.controller('personDetailsController',['$scope','$http','$window','$location',personDetailsController]);
