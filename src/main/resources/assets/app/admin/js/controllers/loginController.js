var loginController = function($scope, $http) {

    $scope.formInfo = {};
    $scope.login = function(){

        $http.post('/api/login',$scope.formInfo).success(function(args){
          alert("success" + args);
        }).
        error(function(args){
                   alert("error" + args);
        });
    }
}

adminApp.controller ('loginController',['$scope', '$http',loginController]);

