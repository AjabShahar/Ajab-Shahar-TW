var songsController = function($scope,contentService,songThumbnailService,introductionPopupService,popupService, $location){
    $scope.url = null;
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.detailsService=popupService;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getSongsLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = songThumbnailService.getThumbnailsWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails({"songs":result.data});
        });
    }

    $scope.open = function(id){
        popupService.open(id);
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

landingPagesApp.controller('songsController',['$scope','contentService','songThumbnailService','introductionPopupService','popupService', '$location',songsController]);