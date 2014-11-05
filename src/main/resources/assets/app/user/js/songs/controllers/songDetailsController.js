var songDetailsController = function($scope,contentService,songDetailsService){
    $scope.popupService = this;
    visibilityOfAllVersions = {};

    $scope.open = function(id){
        _.each(visibilityOfAllVersions, function(detail) {
            visibilityOfAllVersions[key] = false;
        });
        visibilityOfAllVersions[id] = true;
    }

    $scope.init = function(){
        contentService.getSongDetails().then(function(result){
            $scope.carouselSections = songDetailsService.getThumbnailWithBubble(result.data);
            $scope.detailContent = introductionPopupService.getPopupDetails(result.data);
        });
    }
    //carouselSections
};

songDetailsApp.controller('songDetailsController',['$scope','contentService','songDetailsService',songDetailsController]);