var mainEditorsChoiceController = function($scope,contentService,popupService, mappers){
    $scope.detailsService = popupService;
    $scope.url = null;
    $scope.thumbnailDetails={};
    $scope.words = {};

    $scope.init = function(){
        var content = contentService.getMainLandingPageThumbnails();
        
        content.songs.then(function(result){
            $scope.thumbnailDetails = result.data;
            $scope.thumbnailDetails.songThumbnails = mappers.getSongMapper().getThumbnails($scope.thumbnailDetails.songs,$scope.getSongCustomStyle);
            $scope.thumbnailDetails.introductions = mappers.getSongMapper().getIntroductions($scope.thumbnailDetails.songs);

        });

        content.words.then(function(result){
            $scope.thumbnailDetailsForWord = result.data;
            $scope.thumbnailDetailsForWord.wordThumbnails = mappers.getWordMapper().getThumbnails($scope.thumbnailDetailsForWord,$scope.getWordCustomStyle);
            $scope.thumbnailDetailsForWord.wordIntroductions = mappers.getWordMapper().getIntroductions($scope.thumbnailDetailsForWord);
        });
    }();

    $scope.getSongCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.songs, thumbnail));
    }

    $scope.getCoupletCustomStyle = function(thumbnail){
        return $scope.getCustomStyle(_.indexOf($scope.thumbnailDetails.couplets, thumbnail));
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
}

editorsChoiceApp.controller('mainEditorsChoiceController',['$scope','contentService','popupService','mappers',mainEditorsChoiceController]);
