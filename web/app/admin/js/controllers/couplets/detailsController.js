var coupletDetailsController = function ($scope, $http, $window, $location) {
    $scope.formInfo = {};
    $scope.formInfo.poet = {};
    $scope.poetsList = [];
    $scope.categoryList = [];
    $scope.urlId = $location.search().id;


    $http.get('/api/people?role=Poet').success(function (allPoets) {
        $scope.poets = allPoets;
        $scope.poetsList = $scope.poets.people;
    });

    $http.get('/api/category/couplet').success(function (categoryList) {
        $scope.categoryList = categoryList;
    });


    $scope.saveData = function () {
        $scope.formInfo.poet.category = $scope.formInfo.poet.roles[0];
        $http.post('/api/couplets', $scope.formInfo).success(function (data) {
            $window.location.href = '/admin/couplets/edit.html?id=' + data;
        });
    };

    $scope.getCoupletData = function () {
        $http.get('/api/couplets/edit', {
            params: {
                id: $scope.urlId
            }
        })
            .success(function (data) {
                $scope.formInfo = data;
            });
    };

    $scope.updateCouplet = function () {
        $http.put('/api/couplets/edit', $scope.formInfo).success(function (data) {
            alert('data updated');
        });
    };

    $scope.redirectToEnterPage = function () {
        alert('This data is not updated');
        $window.location.href = '/admin/couplets/details.html';
    };
};
adminApp.controller('coupletDetailsController', ['$scope', '$http', '$window', '$location', coupletDetailsController]);
