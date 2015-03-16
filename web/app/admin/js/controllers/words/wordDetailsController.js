wordsAdminApp.controller('wordDetailsController',['$scope', '$window', '$location', 'contentService', 'PAGES', '$q',"loginVerifyService",
function($scope, $window, $location, contentService, PAGES, $q,loginVerifyService){
  loginVerifyService.redirectIfNotAuthenticated();

  $scope.formInfo = {
    reflections: [],
    wordIntroductions: []
  };
  $scope.categoryList = [];
  $scope.reflectionsList = [];
  $scope.peopleList = [];
  $scope.songs = [];

  var wordCategory = 'word';

  var createMenuTitleForSongs = function(){
    angular.forEach($scope.songs, function( song ){
      var singerNames = _.pluck(song.singers, 'name');
      if(_.isEmpty(singerNames)) {
        song.menuTitle = song.englishTransliterationTitle;
      } else {
        song.menuTitle = song.englishTransliterationTitle + " - (" + singerNames.join(", ") + ")";  
      }
      song.words = song.words.words;
    });
  };

  $scope.init = function(){
    var reflectionsPromise = contentService.getAllReflections();
    var peoplePromise = contentService.getAllPeople();
    var categoriesPromise = contentService.getAllCategories(wordCategory);
    var songsPromise = contentService.getAllSongs();

    $q.all([categoriesPromise, songsPromise, peoplePromise, reflectionsPromise]).then(function(data){
      $scope.categoryList = data[0].data;
      $scope.songs = data[1].data.songs;
      $scope.peopleList = data[2].data.people;
      $scope.reflections = data[3].data;

      createMenuTitleForSongs();
      
      getWordDetails();
    });
  };

  $scope.saveData = function(){
      contentService.saveWord($scope.formInfo).success(function(){
          $window.location.href = '/admin/partials/home.html';
      });
  };

  var getSelectedContent = function(data, list){
    return angular.forEach(list, function( item ){
      angular.forEach(data, function(selectedItem){
        if(!item.ticked) {
          item.ticked = (selectedItem.id === item.id)
        }
      });
    });
  };

  var getWordDetails = function(){
    var wordID = $location.search().id;

    if(wordID){
      contentService.getWord(wordID).success(function (data) {
        $scope.reflectionsList   =  getSelectedContent( data.reflection, $scope.reflectionsList );
        $scope.peopleList   =  getSelectedContent( data.writers, $scope.peopleList );
        $scope.songs = getSelectedContent(data.songs, $scope.songs);
        $scope.formInfo = data;
      });
    }
  };

  var redirectToURL = function(url){
    $window.location.href = url;
  };

  $scope.redirectToEnterPage= function(){
    redirectToURL(PAGES.ADMIN_HOME);
  };

  $scope.updateWord = function(){
    contentService.updateWord($scope.formInfo).success(function(){
      redirectToURL(PAGES.ADMIN_HOME);
    });
  };
}]);

