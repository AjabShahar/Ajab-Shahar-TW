adminApp.controller('adminHomeController',['$http','$scope','$window','$cookies','loginVerifyService','$timeout',
    function($http,$scope,$window,$cookies,loginVerifyService,$timeout){
    loginVerifyService.redirectIfNotAuthenticated();
    $scope.message = {
        text:"",
        type:""
    };
    $scope.logout=function(){
        $http.post('/api/logout').success(function(){
            $cookies.user = undefined;
            $window.location.href = '/admin/partials/signin.html';
        }).error(function(response){
            $scope.message.text = "Unable to sign out, make sure you are  signed in";
            $scope.message.type = "error";
            $timeout(function(){
                $scope.message.text="";
            },5000)
        });
    }
}]);
