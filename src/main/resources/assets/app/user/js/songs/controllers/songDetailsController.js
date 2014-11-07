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
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.urlId).then(function(result){
            $scope.renditionDetails = songDetailsService.getSongRenditions(result.data);
        });
    }

    $scope.isVideo = function(id){
        return true;
    }

    $scope.isAudio = function(id){
        return false;
    }

    $scope.init();
};

songDetailsApp.controller('songDetailsController',['$scope','$location','songsContentService','songDetailsService',songDetailsController]);