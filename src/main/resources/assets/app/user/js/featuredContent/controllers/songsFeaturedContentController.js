var songsFeaturedContentController = function($scope,contentService,songMapper, $location, $window){
    $scope.detailsService={open:function(id){
//                                          var songId = $scope.getSongId(id);
                                          $window.location.href = '/songs/details.html?id='+id;
                                      }};;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getSongsLandingPageThumbnails().then(function(result){
            $scope.songs = songMapper.getThumbnails(result.data.songs,$scope.getSongCustomStyle);
        });
    }

    $scope.getSongCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.songs, thumbnail));
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

featuredContentApp.controller('songsFeaturedContentController',['$scope','contentService','songMapper','$location', '$window',songsFeaturedContentController]);