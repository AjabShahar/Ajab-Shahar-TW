var loginController = adminApp.controller ('loginController',['$scope', '$http','$window', function($scope, $http,$window) {

    $scope.formInfo = {};
    $scope.login = function(){

        $http.post('/api/login',$scope.formInfo).success(function(args){
            $window.location.href = '/admin/home.html';
            $cookies.authSessionId = $cookies.JSESSIONID;
        }).
        error(function(args){
           alert("error" + args);
        });
    };
}]);



