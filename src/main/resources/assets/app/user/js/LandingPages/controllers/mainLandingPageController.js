var mainLandingPageController = function($scope,contentService,introductionPopupService,popupService, $location){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.detailsService = popupService;
    $scope.url = null;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailDetails = result.data;
            $scope.popupContent = introductionPopupService.getPopupDetails(result.data);
        });
    }

    $scope.getSongCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.songs, thumbnail));
    }

    $scope.getCoupletCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.couplets, thumbnail) + $scope.thumbnailDetails.songs.length);
    }

    $scope.getCustomStyle =function(id){
        var index = ((4+id) % 6) == 0 ? 6 : ((4+id) % 6);
        return "shift"+index;
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

landingPagesApp.controller('mainLandingPageController',['$scope','contentService','introductionPopupService','popupService', '$location',mainLandingPageController]);