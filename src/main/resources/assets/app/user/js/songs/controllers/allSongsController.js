var allSongsController = function($scope,songsContentService,songThumbnailService,introductionPopupService,popupService){
    $scope.songs=[];
    $scope.songDetails=[];
    $scope.totalSongs = null;
    $scope.detailsService = popupService;
    var i = 0, j;

    $scope.getTotalSongsCount = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.totalSongs = songsList.data.length;
        });
    }

    $scope.loadSongFromRange = function(from , to){
    	songsContentService.getSongsGivenRange(from, to).then(function(result){
            for(j = 0; j < result.data.length ;j++, i++){
                var songThumbnailWithBubble = songThumbnailService.getThumbnailWithBubble(result.data[j],'song_'+result.data[j].id,'');
                $scope.songs.push(songThumbnailWithBubble);

                var popupContent = songThumbnailService.getPopupDetails(result.data[j],j,'song_'+result.data[j].id,j);
                $scope.songDetails.push(popupContent);
            }
        });
    }

    $scope.loadMoreSongs = function(){
        $scope.loadSongFromRange(i, i + 9);
    }
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);