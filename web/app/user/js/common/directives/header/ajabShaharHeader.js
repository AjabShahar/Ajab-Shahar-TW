'use strict';

headerModule.directive("ajabShaharHeader", function ($window) {
    return {
        restrict: 'E',
        scope: {
            showMenuDetails: '@',
            callBack: '&'
        },
        templateUrl: '/user/js/common/templates/header/ajabShaharHeader.html',
        controller: function ($scope) {
            $scope.initialMenuState = $scope.showMenuDetails;
            $scope.currentPage = {"songs": false, "words": false, "reflections": false, "glossary": false,"people":false};
            $window.onload = function () {
                $scope.callBack && $scope.callBack();
            };
            $scope.getElementsByClassName = function (className) {
                return $window.document.getElementsByClassName(className)[0];
            };

            $scope.showMenu = function () {
                if ($scope.initialMenuState && !$scope.showMenuDetails)
                    $scope.toggleMenuAndDigest();
            };

            $scope.hideMenu = function () {
                if ($scope.showMenuDetails)
                    $scope.toggleMenuAndDigest();
            };

            $scope.toggleMenuAndDigest = function () {
                $scope.toggleMenu();
                if (!$scope.$$phase && !$scope.$root.$$phase)
                    $scope.$digest();
            };

            $scope.toggleMenu = function () {
                $scope.showMenuDetails = !$scope.showMenuDetails;
            };

            $scope.init = function () {
                var currentPagePath = $window.location.href;
                if (currentPagePath.indexOf("/songs") > 0)
                    $scope.currentPage.songs = true;
                else if(currentPagePath.indexOf("/words/glossary")>0)
                    $scope.currentPage.glossary = true;
                else if (currentPagePath.indexOf("/words/") > 0)
                    $scope.currentPage.words = true;
                else if (currentPagePath.indexOf("/reflections") > 0)
                    $scope.currentPage.reflections = true;
                else if(currentPagePath.indexOf("/people") > 0)
                    $scope.currentPage.people = true;

            };
            $scope.init();
        }
    }
});
