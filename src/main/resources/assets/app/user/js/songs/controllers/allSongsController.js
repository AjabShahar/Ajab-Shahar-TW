var allSongsController = function($scope,contentService,songThumbnailService,introductionPopupService,popupService){
    $scope.popupService = popupService;

};

allSongsApp.controller('allSongsController',['$scope','contentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);