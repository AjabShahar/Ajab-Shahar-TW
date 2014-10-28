var songBasicDetailsController = function($scope, $http,$window){

  $scope.formInfo = {};
  $scope.formInfo.singers = [];
  $scope.formInfo.poets = [];
  $scope.category = {};
  $scope.singersList = [];
  $scope.poetsList = [];
  $scope.categoryList = [];
  $scope.songDetails = {};

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
    $http.post('/api/songs',$scope.formInfo).success(function(data){
          alert(data);
          $http.get('/api/songs/edit', {
                  params: {
                     id:data
                  }
               })
               .success(function (data,status) {
                    alert(data.id);
                    $scope.songDetails = data;
                    $window.location.href='/partials/admin/songDetails/edit.html'
           });

    });
  };
}

adminApp.controller('songBasicDetailsController',['$scope','$http','$window',songBasicDetailsController]);
