var allSongsController = function($scope,songsContentService,songThumbnailService,introductionPopupService,popupService){
    $scope.detailsService = popupService;
    $scope.thumbnailContent=[];
    $scope.paginationthumbnailContent=null;
    var i = 0, j;

    $scope.init = function(from , to){
    	songsContentService.getSongsGivenRange(from, to).then(function(result){
            for(j = 0; j < result.data.length ;j++, i++){
                var songThumbnailWithBubble = songThumbnailService.getThumbnailWithBubble(result.data[j],'song_'+result.data[j].id,'');
                $scope.thumbnailContent.push(songThumbnailWithBubble);
            }
        });
    }

    $scope.addMoreItems = function(){
        $scope.init(i, i + 9);
    }

};

allSongsApp.controller('allSongsController',['$scope','songsContentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);