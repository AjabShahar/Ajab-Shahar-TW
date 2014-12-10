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
                var value = "" + singers[0].name;
                for(var index=1;index< singers.length;index++){
                   value += ' & '+singers[index].name;
                }
                return value;
            };

            $scope.song = {
                "id":$scope.details.id,
                "contentId":"song_"+$scope.details.id,
                "videoId":$scope.details.youTubeVideoId,
                "englishTranslation":$scope.details.englishTranslationTitle,
                "englishTransliteration":$scope.details.englishTransliterationTitle,
                "singer":getSingers($scope.details.singers),
                "audioId":$scope.details.soundCloudTrackID,
                "poet":($scope.details.poet==null || $scope.details.poet.length==0)?'Unknown': $scope.details.poet[0].name
            };


        }
    }});
