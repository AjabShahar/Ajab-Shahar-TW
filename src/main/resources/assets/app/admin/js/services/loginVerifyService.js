angular.module('adminCommon',['ngCookies'])
.service('loginVerifyService',['$cookies','$window',function($cookies,$window){
    return {
        redirectIfNotAuthenticated: function(){
            if(!$cookies.user || $cookies.user !== 'admin'){
                $window.location.href="/admin/signin.html"
            }
        }
    }
}]);