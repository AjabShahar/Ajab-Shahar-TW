var songsFeaturedContentController = function($scope,contentService,songMapper,popupService, $location, $window){
    $scope.publishedSongsCount = 0;
    $scope.thumbnails = [];
    $scope.featureContentOverviews = [];

    $scope.detailsService = popupService;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getSongsLandingPageThumbnails().then(function(result){
            $scope.publishedSongsCount = result.data.songs.length;
            var songs = _.shuffle(result.data.songs).slice(0,9);
            var songThumbnails = songMapper.getThumbnails(songs, $scope.getSongCustomStyle);

            _.each(songThumbnails, function(thumbnail){
                $scope.thumbnails.push(thumbnail);
            });

            var introductions = songMapper.getOverviews(songs);
            _.each(introductions, function(introduction){
                $scope.featureContentOverviews.push(introduction);
            });

        });
    }

    $scope.openThumbnail = function(elementId){
        return detailsService.open(elementId);
    }

    var index = 0;
    $scope.getSongCustomStyle = function(){
        index++;
        return "shift" + index;
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

featuredContentApp.controller('songsFeaturedContentController',['$scope','contentService','songMapper','popupService','$location', '$window',songsFeaturedContentController]);