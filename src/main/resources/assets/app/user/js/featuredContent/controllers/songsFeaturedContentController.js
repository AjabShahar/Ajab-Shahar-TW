var songsFeaturedContentController = function($scope,contentService,songMapper, $location, $window){
    $scope.detailsService={open:function(id){
//                                          var songId = $scope.getSongId(id);
                                          $window.location.href = '/songs/details.html?id='+id;
                                      }};;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getSongsLandingPageThumbnails().then(function(result){
            var songs = _.shuffle(result.data.songs).slice(9);
            $scope.songs = songMapper.getThumbnails(songs,$scope.getSongCustomStyle);
        });
    }

    $scope.getSongCustomStyle = function(thumbnail){
        return "shift" + _.indexOf($scope.songs, thumbnail);
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