var songDetailsController = function($scope,$location,songsContentService,songDetailsService){
    $scope.detailsService = this;
    $scope.urlId = $location.search().id;
    $scope.detailsService = $scope;

    $scope.init = function(){
        songsContentService.getSongsVersions($scope.urlId).then(function(result){
            $scope.carouselSections = songDetailsService.getThumbnailWithBubble(result.data);
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.urlId).then(function(result){
            $scope.details = songDetailsService.getSongRenditions(result.data);
            $scope.details[0].showContentDetails = true;
        });
    }

    $scope.open = function(id){
        _.each($scope.details, function(detail) {
            detail.showContentDetails = (detail.id == id);
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