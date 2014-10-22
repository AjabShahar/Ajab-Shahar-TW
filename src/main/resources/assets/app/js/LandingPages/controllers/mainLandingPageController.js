var mainLandingPageController = function($scope,contentService,mainLandingPageThumbnailService,introductionPopupService){
    $scope.popupContent=null;
    $scope.thumbnailContent=null;
    $scope.shouldBeOpen={};
    $scope.init = function(){
        contentService.getMainLandingPageThumbnails().then(function(result){
            $scope.thumbnailContent = mainLandingPageThumbnailService.getThumbnailWithBubble(result.data);
            $scope.popupContent = introductionPopupService.getPopupDetails(result.data);
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

mainLandingPageApp.controller('mainLandingPageController',['$scope','contentService','mainLandingPageThumbnailService','introductionPopupService',mainLandingPageController]);