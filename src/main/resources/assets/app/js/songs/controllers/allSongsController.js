var allSongsController = function($scope,contentService,songThumbnailService,introductionPopupService,popupService){
    $scope.popupService = popupService;
    $scope.elements = [{
        src: 'img1.png',
        title: 'Pic 1',
        visible:true
    }, {
        src: 'img2.jpg',
        title: 'Pic 2',
        visible:false
    }, {
        src: 'img3.jpg',
        title: 'Pic 3',
        visible:false
    }, {
        src: 'img4.png',
        title: 'Pic 4',
        visible:false
    }, {
        src: 'img5.png',
        title: 'Pic 5',
        visible:false
    }];
};

allSongsApp.controller('allSongsController',['$scope','contentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);