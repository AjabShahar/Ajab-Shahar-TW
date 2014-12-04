var allSongsController = function($scope,songsContentService,popupService){
    $scope.songs=[];
    $scope.totalSongs = null;
    $scope.totalFilteredSongs = 0;
    $scope.detailsService = popupService;
    $scope.activeLetter = '';

    var i = 0;

    $scope.removeSongs = function(){
        i = 0;
        $scope.songs.splice(0, $scope.songs.length);
    }

    $scope.getTotalSongsCount = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.totalFilteredSongs = $scope.totalSongs = songsList.data.length;
            $scope.loadSongFromRange();
        });
    }

    $scope.filterSongsOnLetter = function(letter){
        $scope.activeLetter = letter;
        $scope.removeSongs();
        $scope.loadSongFromRange();

        songsContentService.getSongsStartingWith($scope.activeLetter).then(function(songsCountJson){
            $scope.totalFilteredSongs = songsCountJson.data;
        });
    }

    $scope.loadSongFromRange = function(){
        if(i <= $scope.totalFilteredSongs){
            songsContentService.getSongsInRangeAndFilteredBy(i, $scope.activeLetter).then(function(result){
                for(j=0; j< result.data.songs.length; j++){
                    $scope.songs.push(result.data.songs[j]);
                }

                i += 9;
            });
        }
    }

    $scope.getTotalSongsCount();
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','popupService',allSongsController]);