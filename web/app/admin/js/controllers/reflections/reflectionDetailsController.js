'use strict';
reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService",
    function ($scope, $window, $location, reflectionContentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.reflection = {"transcripts": [], "speaker": {}};
        $scope.people = [];
        $scope.words = [];
        var urlId = $location.search().id;

        var init = function () {
            reflectionContentService.getPeople().success(function (data) {
                $scope.people = data;
            });

            reflectionContentService.getWords().success(function(data){
                $scope.words = data.words;
            });

            if (urlId != null && urlId != ''){
                reflectionContentService.getRefectionById(urlId).success(function (data) {
                        $scope.reflection = data;
                });
            }

        };

        $scope.saveData = function () {
            reflectionContentService.saveReflection($scope.reflection).success(function (data) {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        init();
    }]);

