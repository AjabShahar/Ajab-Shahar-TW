var mainEditorsChoiceController = function($scope,contentService,popupService, $location){
    $scope.detailsService = popupService;
    $scope.url = null;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailDetails = result.data;
            $scope.totalNumberOfThumbnails = $scope.thumbnailDetails.songs.length>9 ?9 :$scope.thumbnailDetails.songs.length;
        });
    }

    $scope.getSongCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.songs, thumbnail));
    }

    $scope.getCoupletCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.couplets, thumbnail) + $scope.thumbnailDetails.songs.length);
    }

    $scope.getWordCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.words, thumbnail));
    }

    $scope.getCustomStyle =function(id){
//        var index = ((4+id) % 6) == 0 ? 6 : ((4+id) % 6);
          var index = id + 1;
//        console.log(id);
        return "shift"+index;
    }

    $scope.setPageHeight = function(){
//        var homepageContent = jQuery('.' + 'homepage-content')[0];
//        var pxLayers = jQuery('.' + 'pxLayers');
//
//        _.each(pxLayers, function(pxLayer){
//            pxLayer.style.height = (homepageContent.offsetHeight+400)+"px";
//        });
//        homepageContent.style.height = (homepageContent.offsetHeight+300)+"px";
    }
    $scope.init();
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService', '$location',mainEditorsChoiceController]);