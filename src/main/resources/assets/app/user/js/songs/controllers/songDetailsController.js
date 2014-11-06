var songDetailsController = function($scope,$location,songsContentService,songDetailsService){
    $scope.detailsService = this;
    visibilityOfAllVersions = {};
    $scope.urlId = $location.search().id;
    $scope.open = function(id){
        _.each(visibilityOfAllVersions, function(detail) {
            visibilityOfAllVersions[key] = false;
        });
        visibilityOfAllVersions[id] = true;
    }

    $scope.init = function(){
        songsContentService.getSongsVersions($scope.urlId).then(function(result){
            $scope.carouselSections = songDetailsService.getThumbnailWithBubble(result.data);
            $scope.detailContent = '';
        });
    }

    $scope.init();
    //carouselSections
};

songDetailsApp.controller('songDetailsController',['$scope','$location','songsContentService','songDetailsService',songDetailsController]);