var songDetailsController = function($scope, $window,$location,songContentService, $filter){
    $scope.formInfo = {
      singers:[],
      poets: [],
      songText: {},
      isAuthoringComplete: false
    };
    $scope.singersList = [];
    $scope.poetsList = [];
    $scope.songCategoryList = [];
    $scope.mediaCategoryList = [];
    $scope.umbrellaTitleList = [];
    $scope.songTitleList = [];
    $scope.coupletList = [];
    $scope.genreList = [];

    $scope.init = function(){
      songContentService.getAllUmbrellaTitles().success(function(umbrellaTitleList){
          $scope.umbrellaTitleList = umbrellaTitleList;
      });

      songContentService.getAllSongTitles().success(function(songTitleList){
          $scope.songTitleList= songTitleList;
      });

      songContentService.getAllSingers().success(function(allSingers){
          $scope.singers = allSingers;
          $scope.singersList = $scope.singers.people;
          angular.forEach($scope.singersList,function(singer){
            if(singer.lastName == null)
              singer.lastName = '';
          });
          $scope.singersList = $filter('orderBy')($scope.singersList, 'firstName');
      });

      songContentService.getAllPoets().success(function(allPoets){
          $scope.poets = allPoets.people;
          $scope.poetsList = $scope.poets;
          angular.forEach($scope.poetsList,function(poet){
             if(poet.lastName == null)
               poet.lastName = '';
          });
          $scope.poetsList = $filter('orderBy')($scope.poetsList, 'firstName');

      });

      songContentService.getAllCouplets().success(function(allCouplets){
          $scope.coupletList = allCouplets;
      });

      songContentService.getAllGenres().success(function(genres){
          $scope.genreList = genres;
      });

      songContentService.getSongCategories().success(function(categoryList){
          $scope.songCategoryList = categoryList;
      });

      songContentService.getMediaCategories().success(function(categoryList){
          $scope.mediaCategoryList = categoryList;
      });
    };

    var isVideoOrAudioSong = function(){
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
    };

    $scope.saveData = function(){
      isVideoOrAudioSong();
      
      songContentService.createSong($scope.formInfo).success(function(data){
            $window.location.href = '/admin/songs/edit.html?id='+data;
      });
    };

    var getSelectedContent = function(data, list){
      return angular.forEach(list, function( item ){
        angular.forEach(data,function(selectedItem){
          if(selectedItem.id === item.id)
            item.ticked=true;
          });
      });
    };

    $scope.getSongData = function(){
      $scope.urlId = $location.search().id;

      songContentService.getSong($scope.urlId).success(function (data) {
        $scope.genreList   =  getSelectedContent(data.songGenre, $scope.genreList);
        $scope.singersList =  getSelectedContent(data.singers, $scope.singersList);
        $scope.formInfo = data;
        $scope.formInfo.songText = data.songText;
        $scope.formInfo.songText.openingCouplets = data.songText.openingCouplets;
        $scope.formInfo.songText.songTextContents = $filter('orderBy')($scope.formInfo.songText.songTextContents, 'sequenceNumber');
      });
    };

    $scope.updateSong = function(){
      isVideoOrAudioSong();
      $scope.formInfo.publishedDate = null;

      songContentService.editSong($scope.formInfo).success(function(data){
        $window.location.href = '/admin/home.html';
      });
    };

    $scope.redirectToEnterPage= function(isDirty){
      if(isDirty)
        alert('This data is not updated');

      $window.location.href = '/admin/songs/details.html';
    };

    $scope.init();
}

songsAdminApp.controller('songDetailsController',['$scope','$window','$location','songContentService', '$filter',songDetailsController]);