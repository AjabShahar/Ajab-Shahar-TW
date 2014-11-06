var mainLandingPageController = function($scope,contentService,mainLandingPageThumbnailService,introductionPopupService,popupService){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.detailsService = popupService;
    $scope.init = function(){
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = mainLandingPageThumbnailService.getThumbnailWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails(result.data);
        });
    }

    $scope.setPageHeight = function(){
        var homepageContent = jQuery('.' + 'homepage-content')[0];
        var pxLayers = jQuery('.' + 'pxLayers');

        _.each(pxLayers, function(pxLayer){
            pxLayer.style.height = (homepageContent.offsetHeight+400)+"px";
        });
        homepageContent.style.height = (homepageContent.offsetHeight+300)+"px";
    }
    $scope.init();
}

landingPagesApp.controller('mainLandingPageController',['$scope','contentService','mainLandingPageThumbnailService','introductionPopupService','popupService',mainLandingPageController]);