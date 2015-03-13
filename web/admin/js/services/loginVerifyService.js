angular.module('adminCommon',['ngCookies'])
.service('loginVerifyService',['$cookies','$window',function($cookies,$window){
    return {
        redirectIfNotAuthenticated: function(){
            if($cookies.JSESSIONID !== $cookies.authSessionId){
                $window.location.href="/admin/signin.html"
            }
        }
    }
}]);
