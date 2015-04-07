var gatheringListController = gatheringsAdminApp.controller('gatheringListController', ['$scope', 'gatheringContentService', "loginVerifyService",
    function ($scope, gatheringContentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.gatherings = [];
        $scope.init = function () {
            gatheringContentService.getAllGatherings().then(function (result) {
                $scope.gatherings = result.data;
            });
        };

        $scope.init();
    }]);

