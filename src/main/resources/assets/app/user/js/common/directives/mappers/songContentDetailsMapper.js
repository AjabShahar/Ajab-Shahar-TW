'use strict';

thumbnailModule.directive("songContentDetailsMapper", function(nameService) {
    return {
        replace : true,
        restrict: 'E',
        scope: {
            details:'=',
            detailsService:'='
        },
        templateUrl:'/user/js/common/templates/mappers/songContentDetailsMapper.html',
        controller:function($scope){
            $scope.song = {"id":"song_"+$scope.details.id, "videoId":$scope.details.youtubeVideoId,"audioUrl":$scope.details.soundCloudTrackID};
        }
    }
});