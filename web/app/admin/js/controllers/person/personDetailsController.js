personAdminApp.controller('personDetailsController', ['$scope', '$http', '$window', '$location', 'contentService', 'loginVerifyService',
    function ($scope, $http, $window, $location, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.pageHeading = "";
        $scope.pageTitle = "";
        $scope.formInfo = {};
        $scope.categoryList = [];
        var isAddNewPersonPage = true;

        $scope.init = function () {
            contentService.getAllCategories('person').success(function (result) {
                $scope.categoryList = result;
                getPersonData();
            });
        };

        var savePerson = function () {
            $http.post('/api/people', $scope.formInfo).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        var updatePerson = function () {
            $http.post('/api/people/edit', $scope.formInfo).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        $scope.saveData = function () {
            if ($scope.formInfo.primaryOccupation == null || ($scope.formInfo.primaryOccupationId != $scope.formInfo.primaryOccupation.id))
                $scope.formInfo.primaryOccupation = _.findWhere($scope.categoryList, {"id": $scope.formInfo.primaryOccupationId});

            (isAddNewPersonPage) ? savePerson() : updatePerson();
        };

        var getPersonDetails = function (personId) {
            $http.get('/api/people/' + personId).success(function (data) {
                $scope.formInfo = data;

                angular.forEach(data.roles, function (selectedCategoryName) {
                    angular.forEach($scope.categoryList, function (category) {
                        if (!category.ticked) {
                            category.ticked = (selectedCategoryName === category.name)
                        }
                    });
                });

                $scope.formInfo.primaryOccupationId = Boolean($scope.formInfo.primaryOccupation != null) ? $scope.formInfo.primaryOccupation.id : '';
            });
        };

        var getPersonData = function () {
            var personId = $location.search().id;
            isAddNewPersonPage = (personId == undefined);

            if (isAddNewPersonPage) {
                $scope.pageTitle = "Person details - admin";
                $scope.pageHeading = "Add Person details";
            }
            else {
                $scope.pageTitle = "Edit person details";
                $scope.pageHeading = "Edit Person details";
                getPersonDetails(personId);
            }
        };
    }]);

