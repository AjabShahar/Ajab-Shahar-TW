var allSongsController = function($scope,songsContentService,songThumbnailService,introductionPopupService,popupService){
    $scope.songs=[];
    $scope.songDetails=[];
    $scope.totalSongs = null;
    $scope.detailsService = popupService;
    $scope.activeLetter = 'f';
    $scope.filterOn = true;

    var i = 0;

    $scope.getTotalSongsCount = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.totalSongs = songsList.data.length;
        });
    }

    $scope.filterBasedOnLetter = function(input, letter){
        input = input || [];
        var out = [];
        input.forEach(function (item) {
            if (item.songTitle.englishTranslation.charAt(0).toLowerCase() == letter) {
                out.push(item);
            }
        });
        return out;
    }

    $scope.createSongThumbnailWithPopUp = function(result){
        var j;
        for(j = 0; j < result.length ;j++, i++){
                var songThumbnailWithBubble = songThumbnailService.getThumbnailWithBubble(result[j],'song_'+result[j].id,'');

                $scope.songs.push(songThumbnailWithBubble);

                var popupContent = songThumbnailService.getPopupDetails(result[j],j,'song_'+result[j].id,j);
                $scope.songDetails.push(popupContent);
            }
    }

    $scope.loadSongFromRange = function(from , to){
    	songsContentService.getSongsGivenRange(from, to).then(function(result){
            var filteredResult = null;

            if(!$scope.filterOn){
                filteredResult = $scope.filterBasedOnLetter(result.data, $scope.activeLetter);
            }
            else {
                filteredResult = result.data;
            }

            $scope.createSongThumbnailWithPopUp(filteredResult);
        });
    }

    $scope.loadMoreSongs = function(){
        $scope.loadSongFromRange(i, i + 9);
    }
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);