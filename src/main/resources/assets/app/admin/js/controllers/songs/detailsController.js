var songDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {};
  $scope.formInfo.singers = [];
  $scope.formInfo.poets = [];
  $scope.singersList = [];
  $scope.poetsList = [];
  $scope.title = {};
  $scope.categoryList = [];
  $scope.songCategoryList = [];
  $scope.mediaCategoryList = [];
  $scope.umbrellaTitleList = [];
  $scope.urlId = $location.search().id;

  $http.get('/api/umbrella').success(function(umbrellaTitleList){
    $scope.umbrellaTitleList = umbrellaTitleList;
  });

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
    var youtubeIdIsNull = $scope.formInfo.youtubeVideoId == undefined || $scope.formInfo.youtubeVideoId == "";

    if(youtubeIdIsNull){
      $scope.formInfo["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio only";
      })[0];
    }
    else {
      $scope.formInfo["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio & video";
      })[0];
    }

    $http.post('/api/songs', $scope.formInfo).success(function(data){
          $window.location.href = '/admin/partials/songs/edit.html?id='+data;
    });
  };


  $scope.getSongData = function(){
      $http.get('/api/songs/'+$scope.urlId)
            .success(function (data,status) {
                          $scope.formInfo = data;

      });
    };

  $scope.updateSong = function(){
     var youtubeIdIsNull = $scope.formInfo.youtubeVideoId == undefined || $scope.formInfo.youtubeVideoId == "";

         if(youtubeIdIsNull){
           $scope.formInfo["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
             return mediaCategory.name == "audio only";
           })[0];
         }
         else {
           $scope.formInfo["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
             return mediaCategory.name == "audio & video";
           })[0];
         }

     $http.put('/api/songs/edit',
            {
              id:$scope.urlId,
              data:$scope.formInfo
            }
     ).success(function(data){
            alert('data updated');
     });
  };

  $scope.redirectToEnterPage= function(){
   alert('This data is not updated');
    $window.location.href = '/admin/partials/songs/details.html';
  };

  $scope.enableNew = function(){
      $scope.formInfo.title = {"selected":null};
      return $scope.AddNewDiv = true;
  }

  $scope.disableNew = function (){
     $http.get('/api/songs/'+$scope.urlId)
                 .success(function (data,status) {
                               $scope.formInfo = data;

     });
     return $scope.AddNewDiv = false;
  }
}

adminApp.controller('songDetailsController',['$scope','$http','$window','$location',songDetailsController]);
