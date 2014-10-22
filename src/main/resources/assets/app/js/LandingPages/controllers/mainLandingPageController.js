var mainLandingPageController = function($scope,contentService,mainLandingPageThumbnailService,introductionPopupService,popupService){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.popupService = popupService;
    $scope.init = function(){
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = mainLandingPageThumbnailService.getThumbnailWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails(result.data);
        });
    }

    $scope.init();
}

ajabShaharApp.controller('mainLandingPageController',['$scope','contentService','mainLandingPageThumbnailService','introductionPopupService','popupService',mainLandingPageController]);