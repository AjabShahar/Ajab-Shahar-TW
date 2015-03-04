songsAdminApp.controller('songDetailsController',['$scope', '$window', '$location', 'songContentService', 'PAGES', '$filter', '$q',"loginVerifyService",
function($scope, $window, $location, songContentService, PAGES, $filter, $q,loginVerifyService){
  loginVerifyService.redirectIfNotAuthenticated();
  $scope.song = {
    singers:[],
    poets: [],
    songText: {},
    isAuthoringComplete: false,
    mediaCategory: {},
    words : [],
    songCategory: ""
  };
  $scope.singers = [];
  $scope.poets = [];
  $scope.songCategories = [];
  $scope.mediaCategories = [];
  $scope.umbrellaTitles = [];
  $scope.songTitles = [];
  $scope.genres = [];
  $scope.words = [];

  var sortList = function(list, sortCriteria){
    return $filter('orderBy')(list, sortCriteria);
  };

  var removeNulls = function(personList){
    var persons = angular.forEach(personList, function(person){
      person.lastName = (Boolean(person.lastName)) ? person.lastName : '';
    });
    return sortList(persons, 'firstName');
  };

  $scope.isEmpty = function(value){
    return !Boolean(value);
  }

  $scope.init = function(){
    var genrePromise = songContentService.getGenres();
    var titlePromise = songContentService.getSongTitles();
    var songCategoryPromise = songContentService.getSongCategories();
    var mediaCategoryPromise = songContentService.getMediaCategories();
    var umbrellaTitlePromise = songContentService.getUmbrellaTitles();
    var singerPromise = songContentService.getSingers();
    var poetPromise = songContentService.getPoets();
    var wordPromise = songContentService.getWords();
    var promises = [genrePromise,titlePromise,songCategoryPromise,mediaCategoryPromise,umbrellaTitlePromise,singerPromise,poetPromise,wordPromise];

    $q.all(promises).then(function (data) {
        $scope.genres = data[0].data;
        $scope.songTitles = data[1].data;
        $scope.songCategories = data[2].data;
        $scope.mediaCategories = data[3].data;
        $scope.umbrellaTitles = data[4].data;
        $scope.singers = removeNulls(data[5].data.people);
        $scope.poets = removeNulls(data[6].data.people);
        $scope.words = data[7].data.words;

        $scope.song.songCategory = $scope.songCategories[0];

        $scope.getSongData();
    });
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
        if(!item.ticked) {
          item.ticked = (selectedItem.id === item.id);
        }
      });
    });
  };

  $scope.getSongData = function(){
    var songId = $location.search().id;

    songContentService.getSong(songId).success(function ( data ) {
      $scope.genres   =  getSelectedContent( data.songGenre, $scope.genres );
      $scope.singers =  getSelectedContent( data.singers, $scope.singers );
      $scope.words =  getSelectedContent( data.words, $scope.words );
      $scope.song = data;

        if($scope.song.songText){
          $scope.song.songText.songTextContents = sortList($scope.song.songText.songTextContents, 'sequenceNumber');
        }
    });
  };

  $scope.updateSong = function(){
    setSongCategory();
    $scope.song.publishedDate = null;

    songContentService.editSong($scope.song).success(function(){ redirectToURL(PAGES.ADMIN_HOME); });
  };
}]);


