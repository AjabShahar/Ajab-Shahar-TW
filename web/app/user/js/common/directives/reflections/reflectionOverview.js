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
            };

            $scope.getReflectionUrl = function(){
                return AjabShahar.user.ContentUrlCreator.getUrl({id:$scope.id, title:$scope.title},"reflection")
            };

            $scope.getPersonUrl = function(person){
                return person ? AjabShahar.user.ContentUrlCreator.getUrl(person,"person"):"";
            };

            $scope.getWordUrl = function(word){
                return word? AjabShahar.user.ContentUrlCreator.getUrl(word,"word"):"";
            };

            $scope.getSongUrl = function(song){
                return song? AjabShahar.user.ContentUrlCreator.getUrl(song,"song") :"";
            };
        }
    }
});
