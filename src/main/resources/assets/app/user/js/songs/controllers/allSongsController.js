var allSongsController = function($scope,songsContentService,songThumbnailService,introductionPopupService,popupService){
    $scope.songs=[];
    var i = 0, j;

    $scope.loadSongFromRange = function(from , to){
    	songsContentService.getSongsGivenRange(from, to).then(function(result){
            for(j = 0; j < result.data.length ;j++, i++){
                var songThumbnailWithBubble = songThumbnailService.getThumbnailWithBubble(result.data[j],'song_'+result.data[j].id,'');
                $scope.songs.push(songThumbnailWithBubble);
            }
        });
    }

    $scope.loadMoreSongs = function(){
        $scope.loadSongFromRange(i, i + 9);
    }

};

allSongsApp.controller('allSongsController',['$scope','songsContentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);