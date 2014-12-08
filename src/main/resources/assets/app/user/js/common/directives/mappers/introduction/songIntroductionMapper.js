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
            var getSingers = function(singers){
                var value = "" + singers[0];
                for(var index=1;index< singers.length;index++){
                   value += ' , '+singers[index];
                }
                return value;
            };

            $scope.song = {
                "id":$scope.details.id,
                "contentId":"song_"+$scope.details.id,
                "videoId":$scope.details.youtubeVideoId,
                "englishTranslation":$scope.details.englishTranslationTitle,
                "singer":getSingers($scope.details.singers),
                "audioId":$scope.details.soundCloudTrackID,
                "poet":($scope.details.poet==null || $scope.details.poet.length==0)?'Unknown': $scope.details.poet[0]
            };


        }
    }});
