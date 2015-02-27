var wordDetailsController = function($scope, $window, $location, contentService, PAGES){
  $scope.formInfo = {
    reflections: [],
    wordIntroductions: []
  };
  $scope.categoryList = [];
  $scope.reflectionsList = [];
  $scope.peopleList = [];
  $scope.songs = [];

  var wordCategory = 'word';

  $scope.init = function(){
    contentService.getAllCategories(wordCategory).success(function(wordCategories){
      $scope.categoryList = wordCategories;
    });

    contentService.getAllReflections().success(function(reflections){
      $scope.reflectionsList = reflections;
    });

    contentService.getAllPeople().success(function(people){
      $scope.peopleList = people.people;
    });

    contentService.getAllSongs().success(function(songs){
      $scope.songs = songs;
    });    
  };

  $scope.saveData = function(){
      contentService.saveWord($scope.formInfo).success(function(){
          $window.location.href = '/admin/home.html';
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

  $scope.getWordDetails = function(){
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
  }
};

wordsAdminApp.controller('wordDetailsController',['$scope', '$window', '$location', 'contentService', 'PAGES', wordDetailsController]);
