reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService",
    function ($scope, $window, $location, reflectionContentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.formInfo = {"reflectionTranscripts": [], "speaker": {}};
        $scope.people = [];
        var urlId = $location.search().id;

        var init = function () {
            reflectionContentService.getPeople().success(function (data) {
                $scope.people = data.people;
            });

            if (urlId != null && urlId != ''){
                reflectionContentService.getRefectionById(urlId).success(function (data) {
                        $scope.formInfo = data;
                        $scope.formInfo.speaker = data.speaker;
                });
            }

        };

        $scope.saveData = function () {
            reflectionContentService.saveReflection($scope.formInfo).success(function (data) {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        init();
    }]);

