'use strict';

thumbnailModule.directive("ajabShaharHeader", function($window) {
    return {
        restrict: 'E',
        scope: {
            shouldExpandByDefault:'@',
        },
        templateUrl:'/js/common/templates/common/header.html',
        controller:function($scope){
            $window.onload = function () {
                var homepageContent = $scope.getElementsByClassName('homepage-content');
                var pxLayers = $window.document.getElementsByClassName('pxLayers');

                _.each(pxLayers, function(pxLayer){
                    pxLayer.style.height = (homepageContent.offsetHeight+400)+"px";
                });
                homepageContent.style.height = (homepageContent.offsetHeight+300)+"px";
            }

            $scope.getElementsByClassName = function(className){
                return $window.document.getElementsByClassName(className)[0];
            }

            $scope.showMenuContent = false;
            $scope.showMenu = function(){
                $scope.showMenuContent = !$scope.showMenuContent;

                if($scope.showMenuContent){
                    $scope.fixedOnScroll = 'fixedOnScroll';
                    $scope.headerArtTop = $scope.getElementsByClassName('headerWrapper').offsetHeight;
                }

                if(!$scope.showMenuContent){
                    $scope.fixedOnScroll = '';
                    $scope.headerArtTop = 0;
                }
            }
        }
    }
});