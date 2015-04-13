var reflectionListController = adminApp.controller('reflectionListController', ['$scope', 'contentService', 'loginVerifyService',
    function ($scope, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.reflections = [];
        $scope.init = function () {
            contentService.getAllReflectionSummaries().then(function (result) {
                var allReflections = result.data.reflections;
                $scope.reflections = _.reduce(allReflections, function (reflections, value, index) {
                    var toBeAdded = {};
                    toBeAdded.title = value.title;
                    toBeAdded.id = value.id;
                    toBeAdded.speaker = value.speaker.name;

                    if (value.isAuthoringComplete)
                        toBeAdded.publish = "Yes";
                    else
                        toBeAdded.publish = "No";

                    reflections.push(toBeAdded);
                    return reflections;
                }, []);
            });
        };

        $scope.init();
    }]);
