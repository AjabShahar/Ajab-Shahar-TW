var allSongsController = function($scope,songsContentService,songThumbnailService,introductionPopupService,popupService){
    $scope.detailsService = popupService;
    $scope.thumbnailContent=[];

    $scope.paginationthumbnailContent=null;

    $scope.init = function(){
    	songsContentService.getSongsGivenRange(0, 9).then(function(result){
            for(i = 0; i < 9 ; i++){
                $scope.thumbnailContent[i] = songThumbnailService.getThumbnailWithBubble(result.data[i],'song_'+result.data[i].id,'');
            }
        });
    }

    console.log("Test " + $scope.thumbnailContent[0]);
    $scope.addMoreItems = function(){
        console.log("increase paginatin");
    }

    $scope.init();
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);