var songDetailsController = function($scope, $window, $location, songContentService, PAGES, $filter){
  $scope.song = {
    singers:[],
    poets: [],
    songText: {},
    isAuthoringComplete: false,
    mediaCategory: {},
    words : []
  };
  $scope.singers = [];
  $scope.poets = [];
  $scope.songCategories = [];
  $scope.mediaCategories = [];
  $scope.umbrellaTitles = [];
  $scope.songTitles = [];
  $scope.genres = [];
  $scope.words = [{text: "w1"}, {text: "w2"}];

  var sortList = function(list, sortCriteria){
    return $filter('orderBy')(list, sortCriteria);
  }

  var removeNulls = function(personList){
    var persons = angular.forEach(personList, function(person){
      person.lastName = (Boolean(person.lastName)) ? person.lastName : '';
    });
    return sortList(persons, 'firstName');
  }

  $scope.init = function(){
    songContentService.getGenres().success(function(genres){ $scope.genres = genres; });

    songContentService.getSongTitles().success(function(titles){ $scope.songTitles= titles; });

    songContentService.getSongCategories().success(function(categories){ $scope.songCategories = categories; });

    songContentService.getMediaCategories().success(function(categories){ $scope.mediaCategories = categories; });

    songContentService.getUmbrellaTitles().success(function(umbrellaTitles){ $scope.umbrellaTitles = umbrellaTitles; });

    songContentService.getSingers().success(function(singers){ $scope.singers = removeNulls(singers.people); });

    songContentService.getPoets().success(function(poets){ $scope.poets = removeNulls(poets.people); });
  };

  var setSongCategory = function(){
    if(Boolean($scope.song.youtubeVideoId )){
      $scope.song["mediaCategory"] =  $scope.mediaCategories.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio & video";
      })[0];
    }
    else {
      $scope.song["mediaCategory"] =  $scope.mediaCategories.filter(function( mediaCategory ) {
        return mediaCategory.name == "audio only";        
      })[0];
    }
  };

  var redirectToURL = function(url){
    $window.location.href = url;
  };

  $scope.saveData = function(){
    setSongCategory();
    
    songContentService.createSong($scope.song).success(function(){ redirectToURL(PAGES.ADMIN_HOME); });
  };

  var getSelectedContent = function(data, list){
    return angular.forEach(list, function( item ){
      angular.forEach(data, function(selectedItem){
        item.ticked = (selectedItem.id === item.id) ? true : false;
      });
    });
  };

  $scope.getSongData = function(){
    var songId = $location.search().id;

    songContentService.getSong(songId).success(function ( data ) {
      $scope.genres   =  getSelectedContent( data.songGenre, $scope.genres );
      $scope.singers =  getSelectedContent( data.singers, $scope.singers );
      $scope.song = data;
      $scope.song.songText.songTextContents = sortList($scope.song.songText.songTextContents, 'sequenceNumber');
    });
  };

  $scope.updateSong = function(){
    setSongCategory();
    $scope.song.publishedDate = null;

    songContentService.editSong($scope.song).success(function(){ redirectToURL(PAGES.ADMIN_HOME); });
  };
}

songsAdminApp.controller('songDetailsController',['$scope', '$window', '$location', 'songContentService', 'PAGES', '$filter', songDetailsController]);