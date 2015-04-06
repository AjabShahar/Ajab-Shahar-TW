var loginController = adminApp.controller('loginController', ['$scope', '$http', '$window', '$cookies', '$timeout',
    function ($scope, $http, $window, $cookies, $timeout) {
        $scope.formInfo = {};
        var timeoutPromise = null;
        $scope.login = function () {
            $http.post('/api/login', $scope.formInfo)
                .success(function () {
                    $cookies.authSessionId = $cookies.JSESSIONID;
                    $window.location.href = '/admin/partials/home.html';
                }).error(function (args, status) {
                    if(status === 503)
                        $scope.message = "The server is temporarily unable to service your request due to maintenance downtime or capacity problems. Please try again later.";
                    else
                        $scope.message = args;
                    $timeout.cancel(timeoutPromise);
                    timeoutPromise = $timeout(function () {
                        $scope.message = "";
                    }, 6000);
                });
        };
    }]);



