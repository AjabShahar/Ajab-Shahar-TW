var songsController = function($scope,contentService,songThumbnailService,introductionPopupService,popupService){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.popupService = popupService;
    $scope.init = function(){
        contentService.getSongsLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = songThumbnailService.getThumbnailsWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails({"songs":result.data});
        });
    }

    $scope.init();
}

ajabShaharApp.controller('songsController',['$scope','contentService','songThumbnailService','introductionPopupService','popupService',songsController]);