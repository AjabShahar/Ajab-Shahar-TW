var songBasicDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.formInfo.singers = [];
  $scope.formInfo.poets = [];
  $scope.category = {};
  $scope.singersList = [];
  $scope.poetsList = [];
  $scope.categoryList = [];

  $http.get('/api/people/singers').success(function(singersList){
    $scope.singersList = singersList;
  });

  $http.get('/api/people/poets').success(function(poetsList){
    $scope.poetsList = poetsList;
  });

  $http.get('/api/category').success(function(categoryList){
    $scope.categoryList = categoryList;
  });

  $scope.saveData = function(){
    $http.post('/api/songs',$scope.formInfo).success(function(){
            alert("Data added");
         });
    } ;
};

adminApp.controller('songBasicDetailsController',['$scope','$http',songBasicDetailsController]);
