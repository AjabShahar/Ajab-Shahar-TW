var allSongsController = function($scope,songsContentService,popupService){
    $scope.songs=[];
    $scope.allSongs = null;
    $scope.totalFilteredSongs = 0;
    $scope.detailsService = popupService;
    $scope.activeLetter = '';
    $scope.scrollIndex = 9;
    $scope.singerNameInFilter = {name:''};
    $scope.poetNameInFilter = {name:''};
    $scope.songCount = 0;

    var i = 0;

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.songs = songsList.data.songs;
            $scope.songCount = songsList.data.songs.length;
        });
    }

    $scope.songStartsWithComparator = function (actual, expected) {
       if (!$scope.activeLetter && $scope.activeLetter=='') {
          return true;
       }
       return $scope.strStartsWith(actual.englishTransliterationTitle.toUpperCase(),$scope.activeLetter.toUpperCase());
    };

    $scope.open = function(id){
        var songId = $scope.getSongId(id);
        $window.location.href = '/user/partials/songs/details.html?id='+songId;
    }

    $scope.more = function(){
        $scope.scrollIndex += 9;
    }

    $scope.getAllSongs();
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','popupService',allSongsController]);