var songBasicDetailsController = function($scope, $http,$window){

  $scope.formInfo = {};
  $scope.formInfo.singers = [];
  $scope.formInfo.poets = [];
  $scope.category = {};
  $scope.singersList = [];
  $scope.poetsList = [];
  $scope.categoryList = [];
  $scope.songDetails = {};
  $scope.songCategoryList = [];
  $scope.mediaCategoryList = [];

  $http.get('/api/people/singers').success(function(singersList){
    $scope.singersList = singersList;
  });

  $http.get('/api/people/poets').success(function(poetsList){
    $scope.poetsList = poetsList;
  });

  $http.get('/api/category/song').success(function(categoryList){
    $scope.songCategoryList = categoryList;
  });

  $http.get('/api/category/media').success(function(categoryList){
      $scope.mediaCategoryList = categoryList;
    });

  $scope.saveData = function(){
    $http.post('/api/songs',$scope.formInfo).success(function(data){
          $window.location.href = '/partials/admin/songDetails/edit.html?id='+data;

    });
   };

    $scope.getSongData = function(){

      $http.get('/api/songs/edit', {
                        params: {
                           id:data
                        }
                     })
                     .success(function (data,status) {
                          $scope.songDetails = data;
      });
    };
}

adminApp.controller('songBasicDetailsController',['$scope','$http','$window',songBasicDetailsController]);
