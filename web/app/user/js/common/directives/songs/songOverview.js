'use strict';

thumbnailModule.directive("songOverview", function () {
    return {
        restrict: 'E',
        scope: {
            englishTranslation: '@',
            englishTransliteration: '@',
            singers: '=',
            poet: '=',
            videoUrl: '@',
            id: '@',
            audioUrl: '@',
            closeVideo: '&',
            noun: '@',
            downloadurl: '@',
            words: '='
        },
        templateUrl: '/user/js/common/templates/songs/songOverview.html',
        controller: function ($scope) {


            /*$scope.$watch(function () {
                return $scope.closeVideo();
            }, function (newValue) {
                $scope.shouldStopVideo = !newValue;
            });*/

            $scope.isVideo = function () {
                return Boolean($scope.videoUrl);
            };

            $scope.isAudio = function () {
                return !$scope.isVideo();
            };

            var setAudioOrVideo = function () {
                if (Boolean($scope.videoUrl)) {
                    $scope.showVideo = true;
                    (Boolean($scope.audioUrl)) ? $scope.hasAudioAndVideo = true : $scope.hasAudioAndVideo = false;
                }
                else {
                    $scope.showAudio = true;
                }
            };

            $scope.toggleVideoOrAudio = function () {
                if ($scope.showVideo) {
                    $scope.showAudio = true;
                    $scope.showVideo = false;

                }
                else {
                    $scope.showAudio = false;
                    $scope.showVideo = true;
                }
            };

            /*$scope.shouldStopVideo = function () {
                return $scope.detailsService.isClosed($scope.id);
            };*/

            var init= function(){
                $scope.hasAudioAndVideo = false;
                $scope.showVideo = false;
                $scope.showAudio = false;
                setAudioOrVideo();
            };

            $scope.$watch('videoUrl',function(){
                init();
            });

            $scope.$watch('audioUrl',function(){
                init();
            });

            $scope.getSongUrl = function(){
                return AjabShahar.user.ContentUrlCreator.getUrl({id:$scope.id, title:$scope.englishTransliteration},"song")
            };

            $scope.getPersonUrl = function(person){
                return person? AjabShahar.user.ContentUrlCreator.getUrl(person,"person"):"";
            };

            $scope.getWordUrl = function(word){
                return word? AjabShahar.user.ContentUrlCreator.getUrl(word,"word"):"";
            }
        }
    }
});
