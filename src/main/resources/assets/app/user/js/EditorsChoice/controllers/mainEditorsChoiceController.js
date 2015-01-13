var mainEditorsChoiceController = function($scope,contentService,popupService, songMapper, $location){
    $scope.detailsService = popupService;
    $scope.url = null;
    $scope.init = function(){
        $scope.url = $location.absUrl();
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailDetails = result.data;
            $scope.totalNumberOfThumbnails = $scope.thumbnailDetails.songs.length>9 ?9 :$scope.thumbnailDetails.songs.length;
            $scope.thumbnailDetails.thumbnails = songMapper.getSongs($scope.thumbnailDetails.songs,$scope.getSongCustomStyle);
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
          var index = id + 1;
        return "shift"+index;
    }

    $scope.open = function(id){
        $scope.detailsService.open(id);
    }
    $scope.init();
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','songMapper', '$location',mainEditorsChoiceController]);