var mainEditorsChoiceController = function($scope,contentService,popupService, mappers, $location){
    $scope.detailsService = popupService;
    $scope.url = null;
    $scope.thumbnailDetails={};
    $scope.words = {};

    $scope.init = function(){
        $scope.url = $location.absUrl();
        var content = contentService.getMainLandingPageThumbnails();
        
        content.songs.then(function(result){
            $scope.thumbnailDetails = result.data;
            $scope.totalNumberOfThumbnails = result.data.songs.length>9 ?9 :result.data.songs.length;

            $scope.thumbnailDetails.songThumbnails = mappers.getSongMapper().getThumbnails($scope.thumbnailDetails.songs,$scope.getSongCustomStyle);
            $scope.thumbnailDetails.introductions = mappers.getSongMapper().getIntroductions($scope.thumbnailDetails.songs);

        });

        content.words.then(function(result){
            $scope.thumbnailDetailsForWord = result.data;
            $scope.thumbnailDetailsForWord.wordThumbnails = mappers.getWordMapper().getThumbnails($scope.thumbnailDetailsForWord,$scope.getWordCustomStyle);
            $scope.thumbnailDetailsForWord.wordIntroductions = mappers.getWordMapper().getIntroductions($scope.thumbnailDetailsForWord);
        });
    }

    $scope.getSongCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.songs, thumbnail));
    }

    $scope.getCoupletCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.couplets, thumbnail) + $scope.thumbnailDetails.songs.length);
    }

    $scope.getWordCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetailsForWord.words, thumbnail));
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

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','mappers', '$location',mainEditorsChoiceController]);
