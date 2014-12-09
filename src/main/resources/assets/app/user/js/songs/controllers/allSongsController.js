var allSongsController = function($scope,songsContentService,popupService){
    $scope.songs=[];
    $scope.allSongs = null;
    $scope.totalFilteredSongs = 0;
    $scope.detailsService = popupService;
    $scope.activeLetter = '';
    $scope.scrollIndex = 9;
    $scope.singerNameInFilter = '';
    $scope.poetNameInFilter = '';

    var i = 0;

    $scope.clearFilters = function(){
        $scope.activeLetter = '';
        $scope.singerNameInFilter = '';
        $scope.poetNameInFilter = '';
    }

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.songs = songsList.data.songs;
            $scope.totalFilteredSongs = songsList.data.songs.length;
        });
    }

    $scope.strStartsWith = function(str, prefix) {
        return (str+"").indexOf(prefix) === 0;
    }

    $scope.songStartsWithComparator = function (actual, expected) {
       if (!$scope.activeLetter && $scope.activeLetter=='') {
          return true;
       }
       return $scope.strStartsWith(actual.englishTransliterationTitle,$scope.activeLetter.toUpperCase());
    };

    $scope.open = function(id){
        var songId = $scope.getSongId(id);
        $window.location.href = '/user/partials/songs/details.html?id='+songId;
    }

    $scope.more = function(){
        $scope.scrollIndex += 9;
    }

    $scope.loadSongFromRange = function(){
//        if(i <= $scope.totalFilteredSongs){
//            songsContentService.getSongsInRangeAndFilteredBy(i, $scope.activeLetter).then(function(result){
//                for(j=0; j< result.data.songs.length; j++){
//                    $scope.songs.push(result.data.songs[j]);
//                }
//
//                i += 9;
//            });
//        }
    }

    $scope.getAllSongs();
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','popupService',allSongsController]);