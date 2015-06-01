adminApp.controller('personListController', ['$scope', 'contentService', 'loginVerifyService',
    function ($scope, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.songs = [];
        $scope.init = function () {
            contentService.getAllPeople().then(function (result) {
                var allPeople = result.data.people;
                $scope.people = _.reduce(allPeople, function (people, value, index) {
                    var toBeAdded = {};
                    toBeAdded.firstName = value.firstName;
                    toBeAdded.middleName = value.middleName;
                    toBeAdded.lastName = value.lastName;
                    toBeAdded.roles = _.reduce(value.roles, function (memo, value, index) {
                        return memo + ((index != 0) ? ', ' : '') + value;
                    }, '');

                    toBeAdded.publish = value.publish;

                    if (value.publish)
                        toBeAdded.publish = "Yes";

                    else
                        toBeAdded.publish = "No";

                    toBeAdded.id = value.id;
                    people.push(toBeAdded);
                    return people;
                }, []);
            });
        };

        $scope.init();
    }]);

