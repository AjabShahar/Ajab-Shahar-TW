featuredContentApp.controller('songsFeaturedContentController', ['$scope', 'mainLandingContentService', 'overviewMapperService', 'popupService', '$location', function ($scope, mainLandingContentService, overviewMapperService, popupService, $location) {
    $scope.publishedSongsCount = 0;
    $scope.thumbnails = {};
    popupService.reset();

    $scope.init = function () {
        $scope.url = $location.absUrl();
        mainLandingContentService.getSongsLandingPageThumbnails().then(function (result) {
            $scope.publishedSongsCount = result.data.songs.length;
            var songs = _.shuffle(result.data.songs).slice(0, 9);

            _.each(songs, function (song,index) {
                $scope.thumbnails[index] = new AjabShahar.ThumbnailObject(song,"song");
            });

            var introductions = overviewMapperService.toSongOverviews(songs);
            _.each(introductions, function (introduction,index) {
                popupService.addItem(introduction,index);
            });

        });
    };

    $scope.thumbnailsCount = function(){
        return _.values($scope.thumbnails).length;
    };
    $scope.openThumbnail = function (index) {
        popupService.select(index);
    };

    $scope.selectedOverview = function(){
        return popupService.getSelected();
    };

    $scope.getSongCustomStyle = function (index) {
        return "shift" + (index+1);
    };

    $scope.setPageHeight = function () {
        var homepageContent = jQuery('.' + 'homepage-content')[0];
        var pxLayers = jQuery('.' + 'pxLayers');

        _.each(pxLayers, function (pxLayer) {
            pxLayer.style.height = (homepageContent.offsetHeight + 400) + "px";
        });
        homepageContent.style.height = (homepageContent.offsetHeight + 300) + "px";
    };

    $scope.init();

}]);

