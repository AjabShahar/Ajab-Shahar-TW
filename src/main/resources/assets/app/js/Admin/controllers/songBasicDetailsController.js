var songBasicDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.titleList=[];
  $scope.singersList=[];
  $scope.poetsList=[];
  $http.get('/api/Person/Singers').success(function(singersList){
                   $scope.singersList = singersList;

  });
  $http.get('/api/Person/Poets').success(function(poetsList){
                     $scope.poetsList = poetsList;

  });

  $scope.saveData = function(){
  $http.post('/api/Song',$scope.formInfo).success(function(){
          alert("Data added");
       });
  } ;

};

adminApp.controller('songBasicDetailsController',['$scope','$http',songBasicDetailsController]);
