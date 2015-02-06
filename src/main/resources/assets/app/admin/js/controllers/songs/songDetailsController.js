var songDetailsController = function($scope, $window, $location, songContentService, PAGES, $filter){
  $scope.song = {
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

  var sortList = function(list, sortCriteria){
    return $filter('orderBy')(list, sortCriteria);
  }

  var sortAndRemoveNullsFromLastname = function(personList){
    var result = angular.forEach(personList, function(person){
      person.lastName = (Boolean(person.lastName)) ? person.lastName : '';
    });
    return sortList(result, 'firstName');
  }

  $scope.init = function(){
    songContentService.getAllUmbrellaTitles().success(function(umbrellaTitleList){
        $scope.umbrellaTitleList = umbrellaTitleList;
    });

    songContentService.getAllSongTitles().success(function(songTitleList){
        $scope.songTitleList= songTitleList;
    });

    songContentService.getAllSingers().success(function(singers){
        $scope.singersList = sortAndRemoveNullsFromLastname(singers.people);
    });

    songContentService.getAllPoets().success(function(poets){
        $scope.poetsList = sortAndRemoveNullsFromLastname(poets.people);
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

  var setSongCategory = function(){
    if(Boolean($scope.song.youtubeVideoId )){
      $scope.song["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio only";
      })[0];
    }
    else {
      $scope.song["mediaCategory"] =  $scope.mediaCategoryList.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio & video";
      })[0];
    }
  };

  var redirectToURL = function(url){
    $window.location.href = url;
  };

  $scope.saveData = function(){
    setSongCategory();
    
    songContentService.createSong($scope.song).success(function(data){
          redirectToURL(PAGES.EDIT + '?id=' + data);
    });
  };

  var getSelectedContent = function(data, list){
    return angular.forEach(list, function( item ){
      angular.forEach(data,function(selectedItem){
        item.ticked = (selectedItem.id === item.id) ? true : false;
      });
    });
  };

  $scope.getSongData = function(){
    var songId = $location.search().id;

    songContentService.getSong(songId).success(function ( data ) {
      $scope.genreList   =  getSelectedContent( data.songGenre, $scope.genreList );
      $scope.singersList =  getSelectedContent( data.singers, $scope.singersList );
      $scope.song = data;
      $scope.song.songText.songTextContents = sortList($scope.song.songText.songTextContents, 'sequenceNumber');
    });
  };

  $scope.updateSong = function(){
    setSongCategory();
    $scope.song.publishedDate = null;

    songContentService.editSong($scope.song).success(function(data){
      redirectToURL(PAGES.ADMIN_HOME);
    });
  };
}

songsAdminApp.controller('songDetailsController',['$scope', '$window', '$location', 'songContentService', 'PAGES', '$filter', songDetailsController]);