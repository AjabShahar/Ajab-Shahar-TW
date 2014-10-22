var songBasicDetailsController = function($scope, $http){

  $scope.formInfo = {};
  $scope.titleList=[];
  $scope.singersList=[];
  $scope.poetsList=[];
  $http.get('/api/people/singers').success(function(singersList){
                   $scope.singersList = singersList;

  });
  $http.get('/api/people/poets').success(function(poetsList){
                     $scope.poetsList = poetsList;

  });

  $scope.saveData = function(){
  $http.post('/api/songs',$scope.formInfo).success(function(){
          alert("Data added");
       });
  } ;

};

adminApp.controller('songBasicDetailsController',['$scope','$http',songBasicDetailsController]);
