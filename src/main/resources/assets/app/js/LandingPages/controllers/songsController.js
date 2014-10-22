var songsController = function($scope,contentService,songThumbnailService,introductionPopupService){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.shouldBeOpen={};
    $scope.init = function(){
        contentService.getSongsLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = songThumbnailService.getThumbnailsWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails({"songs":result.data});
        });
    }

    $scope.open = function(id){
        $scope.shouldBeOpen[id] = true;
    }

    $scope.onClose = function(id){
        $scope.shouldBeOpen[id] = false;
    }

    $scope.shouldShow = function(id){
        return $scope.shouldBeOpen[id];
    }

    $scope.isClosed = function(id){
        return !$scope.shouldBeOpen[id];
    }

    $scope.init();
}

mainLandingPageApp.controller('songsController',['$scope','contentService','songThumbnailService','introductionPopupService',songsController]);