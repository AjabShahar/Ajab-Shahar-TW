'use strict';

thumbnailModule.directive("songIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@',
            totalCount:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/songIntroductionMapper.html',
        controller:function($scope){
            $scope.song = {
                "id":$scope.details.id,
                "contentId":"song_"+$scope.details.id,
                "videoId":$scope.details.youtubeVideoId,
                "englishTranslation":$scope.details.englishTranslationTitle,
                "singer":$scope.details.singers[0],
                "audioId":$scope.details.soundCloudTrackID,
                "poet":($scope.details.poet==null || $scope.details.poet.length==0)?'Unknown': $scope.details.poet[0]
            };
        }
    }});
