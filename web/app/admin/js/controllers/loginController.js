var loginController = adminApp.controller('loginController', ['$scope', '$http', '$window', '$cookies', '$timeout',
    function ($scope, $http, $window, $cookies, $timeout) {
        $scope.formInfo = {};
        var timeoutPromise = null;
        $scope.login = function () {
            $http.post('/api/login', $scope.formInfo)
                .success(function (args) {
                    $cookies.authSessionId = $cookies.JSESSIONID;
                    $window.location.href = '/admin/partials/home.html';
                }).error(function (args, status) {
                    $scope.message = args;
                    $timeout.cancel(timeoutPromise);
                    timeoutPromise = $timeout(function () {
                        $scope.message = "";
                    }, 6000);
                });
        };
    }]);



