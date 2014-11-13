'use strict';

thumbnailModule.directive("coupletIntroductionMapper", function(nameService) {
    return {
        restrict: 'E',
        scope: {
            details:'=',
            customStyle:'@',
            showDetailsService:'=',
            index:'@'
        },
        templateUrl:'/user/js/common/templates/mappers/introduction/coupletIntroductionMapper.html',
        controller:function($scope){
            $scope.couplet = {"id":"couplet_"+$scope.details.id, "videoId":$scope.details.youtubeVideoId,"englishTranslation":$scope.details.title.englishTranslation,
                            "singer":nameService.getName($scope.details.singers[0]) , "audioId":$scope.details.soundCloudTrackID,
                            "poet":nameService.getName($scope.details.poets[0])
                        };
            $scope.init = function(){
                return $scope.showDetailsService.init($scope.couplet.id);
            }
            $scope.next = function(){
                return $scope.showDetailsService.next($scope.couplet.id);
            }
            $scope.prev = function(){
                return $scope.showDetailsService.prev($scope.couplet.id);
            }
            $scope.shouldShow = function(){
                return $scope.showDetailsService.shouldShow($scope.couplet.id);
            }
            $scope.close = function(){
                return $scope.showDetailsService.onClose($scope.couplet.id);
            }
            $scope.isClosed = function(){
                return $scope.showDetailsService.isClosed($scope.couplet.id);
            }
        }
    }
});