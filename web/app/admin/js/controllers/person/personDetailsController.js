personAdminApp.controller('personDetailsController', ['$scope', '$http', '$window', '$location', 'contentService', 'loginVerifyService',
    function ($scope, $http, $window, $location, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.pageHeading = "";
        $scope.pageTitle = "";
        $scope.formInfo = {};
        $scope.categoryList = [];
        $scope.init = function () {
            contentService.getAllCategories('person').success(function (result) {
                $scope.categoryList = result;
                $scope.primaryOccupationsList = angular.copy(result);
                getPersonData();
            });
        };

        var savePerson = function () {
            $http.post('/api/people', $scope.formInfo).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        $scope.saveData = function () {
            //if ($scope.formInfo.primaryOccupation == null || ($scope.formInfo.primaryOccupationId != $scope.formInfo.primaryOccupation.id)) {
            //    $scope.formInfo.primaryOccupation = _.findWhere($scope.categoryList, {"id": $scope.formInfo.primaryOccupationId});
            //}

            if ($scope.formInfo.thumbnailURL && $scope.formInfo.thumbnailURL.indexOf("http") === -1 && $scope.formInfo.thumbnailURL.indexOf("/images/") === -1) {
                $scope.formInfo.thumbnailURL = '/images/' + $scope.formInfo.thumbnailURL;
            }
            savePerson();
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

            if (!personId) {
                $scope.pageTitle = "Person details - admin";
                $scope.pageHeading = "Add Person details";
            }
            else {
                $scope.pageTitle = "Edit person details";
                $scope.pageHeading = "Edit Person details";
                getPersonDetails(personId);
            }
        };
        $scope.$watch("formInfo.primaryOccupation", function (newValue) {
            $scope.categoryList = _.reject($scope.categoryList, function (categoty) {
                return !_.isEmpty(newValue) && categoty.id === newValue.id;
            })
        })
    }]);

