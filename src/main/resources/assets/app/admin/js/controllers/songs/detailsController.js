var songDetailsController = function($scope, $http,$window,$location){

  $scope.formInfo = {
    singers:[]
  };
//  $scope.formInfo.singers = [];
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
  $scope.lyricsTextList = [];
  $scope.coupletList = [];
  $scope.selectedLyricsText;

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

  $http.get('/api/couplets/all').success(function(allCouplets){
    $scope.coupletList = allCouplets;
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
        $location.url($location.absUrl());
        $scope.url = $location.absUrl();
        $scope.urlId = $location.search().id;
        $http.get('/api/songs/'+$scope.urlId)
            .success(function (data,status) {
                          $scope.formInfo = data;

      });
    };

  $scope.addLyricsText = function(){
    if($scope.lyricsText != ""){
      $scope.lyricsTextList.push($scope.lyricsText);
    }
    $scope.lyricsText = "";
  }

  Array.prototype.insert = function (index, item) {
    this.splice(index, 0, item);
  };

  $scope.moveItemUp = function(){
    var selectedSongIndex = $scope.lyricsTextList.indexOf($scope.selectedLyricsText);
    var songToBeMovedUp = $scope.lyricsTextList.splice(selectedSongIndex, 1);

    $scope.lyricsTextList.insert(selectedSongIndex - 1, songToBeMovedUp[0].englishTransliteration);

  }

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
}

songsAdminApp.controller('songDetailsController',['$scope','$http','$window','$location',songDetailsController]);
