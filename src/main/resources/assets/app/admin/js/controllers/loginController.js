var loginController = function($scope, $http,$window) {

    $scope.formInfo = {};
    $scope.login = function(){

        $http.post('/api/login',$scope.formInfo).success(function(args){
          $window.location.href = '/admin/home.html';
        }).
        error(function(args){
           alert("error" + args);
        });
    };
};

adminApp.controller ('loginController',['$scope', '$http','$window',loginController]);

