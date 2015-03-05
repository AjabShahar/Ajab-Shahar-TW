var loginController = adminApp.controller ('loginController',['$scope', '$http','$window','$cookies', function($scope, $http,$window,$cookies) {

    $scope.formInfo = {};
    $scope.login = function(){

        $http.post('/api/login',$scope.formInfo).success(function(args){
            $cookies.authSessionId = $cookies.JSESSIONID;
            $window.location.href = '/admin/home.html';
        }).
        error(function(args){
           alert("error" + args);
        });
    };
}]);



