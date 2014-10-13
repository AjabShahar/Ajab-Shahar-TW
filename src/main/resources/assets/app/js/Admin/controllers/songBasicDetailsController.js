var songBasicDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.titleList=[];
  $scope.singerList=[];
  $http.get('/api/Title').success(function(titleList){
                 $scope.titleList = titleList;
  });
  $http.get('/api/Person').success(function(singerList){
                   $scope.singerList = singerList;

  });
};

adminApp.controller('songBasicDetailsController',['$scope','$http',songBasicDetailsController]);
