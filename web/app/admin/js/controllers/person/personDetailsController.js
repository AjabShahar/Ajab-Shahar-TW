personAdminApp.controller('personDetailsController', ['$scope', '$http', '$window', '$location', 'contentService', 'loginVerifyService',
    function ($scope, $http, $window, $location, contentService, loginVerifyService) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.formInfo = {};
        $scope.categoryList = [];
        $scope.allCategories = [];
        $scope.init = function () {
            contentService.getAllCategories('person').success(function (result) {
                $scope.categoryList = result;
                $scope.allCategories = angular.copy(result);
                $scope.primaryOccupationsList = angular.copy(result);
                getPersonDetails();
            });
        };

        var savePerson = function () {
            $http.post('/api/people', $scope.formInfo).success(function () {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        $scope.saveData = function () {
            if ($scope.formInfo.thumbnailURL && $scope.formInfo.thumbnailURL.indexOf("http") === -1 && $scope.formInfo.thumbnailURL.indexOf("/images/") === -1) {
                $scope.formInfo.thumbnailURL = '/images/' + $scope.formInfo.thumbnailURL;
            }
            savePerson();
        };

        var getPersonDetails = function () {
            var personId = $location.search().id;
            if(personId){
                $http.get('/api/people/' + personId).success(function (data) {
                    $scope.formInfo = data;

                    angular.forEach(data.roles, function (selectedCategoryName) {
                        angular.forEach($scope.categoryList, function (category) {
                            if (!category.ticked) {
                                category.ticked = (selectedCategoryName === category.name)
                            }
                        });
                    });
                    $scope.allCategories = angular.copy($scope.categoryList);
                });
            }
        };

        $scope.$watch("formInfo.primaryOccupation", function (newValue) {
            $scope.categoryList = _.reject($scope.allCategories, function (categoty) {
                return !_.isEmpty(newValue) && categoty.id === newValue.id;
            })
        })
    }]);

