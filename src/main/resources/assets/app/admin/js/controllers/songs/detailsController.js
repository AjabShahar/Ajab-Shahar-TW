var songDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {};
  $scope.formInfo.singers = [];
  $scope.formInfo.poets = [];
  $scope.singersList = [];
  $scope.poetsList = [];
  $scope.title = {};
  $scope.songTitle = {};
  $scope.categoryList = [];
  $scope.songCategoryList = [];
  $scope.mediaCategoryList = [];
  $scope.umbrellaTitleList = [];
  $scope.songTitleList = [];
  $scope.urlId = $location.search().id;

  $http.get('/api/title/umbrella').success(function(umbrellaTitleList){
    $scope.umbrellaTitleList = umbrellaTitleList;
  });

  $http.get('/api/title/song').success(function(songTitleList){
      $scope.songTitleList= songTitleList;
  });

  $http.get('/api/people?role=Singer').success(function(allSingers){
    $scope.singers = allSingers;
    $scope.singersList = $scope.singers.people;
  });

  $http.get('/api/people?role=Poet').success(function(allPoets){
    $scope.poets = allPoets;
    $scope.poetsList = $scope.poets.people;
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

     $http.put('/api/songs/edit',$scope.formInfo).success(function(data){
            alert('data updated');
     });
  };

  $scope.redirectToEnterPage= function(){
   alert('This data is not updated');
    $window.location.href = '/admin/partials/songs/details.html';
  };

  $scope.enableNew = function(){
     $scope.formInfo.title = {"selected":null};
        $http.get('/api/category/umbrellaTitle').success(function(data){
          $scope.formInfo.title.category = data;
        }) ;

     return $scope.AddNewDiv = true;
  }

  $scope.disableNew = function (){
     $http.get('/api/songs/'+$scope.urlId)
                 .success(function (data,status) {
                               $scope.formInfo = data;

     });
     return $scope.AddNewDiv = false;
  }
  $scope.enableNewSongTitle = function(){
   $scope.formInfo.songTitle ={"selected":null};
      $http.get('/api/category/songTitle').success(function(data){
         $scope.formInfo.songTitle.category = data;
      })
   return $scope.AddNewSongTitle = true;
  }
  $scope.disableNewSongTitle = function(){
   $http.get('/api/songs/'+$scope.urlId)
                    .success(function (data,status) {
                                  $scope.formInfo = data;

   });
   return $scope.AddNewSongTitle = false;
  }
}

adminApp.controller('songDetailsController',['$scope','$http','$window','$location',songDetailsController]);
