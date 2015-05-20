'use strict';

thumbnailModule.directive("reflectionOverview", function () {
    return {
        restrict: 'E',
        scope: {
            id: '@',
            closeVideo: '&',
            title: '@',
            verb: '@',
            speaker: '=',
            videoUrl: '@',
            audioUrl: '@',
            text: '=',
            people: '=',
            words: '=',
            songs: '=',
            info: '@'
        },
        templateUrl: '/user/js/common/templates/reflections/reflectionOverview.html',
        controller: function ($scope) {
            $scope.showVideo = Boolean($scope.videoUrl);
            $scope.showAudio = Boolean($scope.audioUrl);
            $scope.showText = Boolean($scope.info) || Boolean($scope.text[0]);

            $scope.$watch(function () {
                return $scope.closeVideo();
            }, function (newValue) {
                $scope.shouldStopVideo = !newValue;
            });

            $scope.shouldStopVideo = function () {
                return $scope.detailsService.isClosed($scope.id);
            }
        }
    }
});
