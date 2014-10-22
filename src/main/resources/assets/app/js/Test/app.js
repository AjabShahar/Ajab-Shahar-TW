var myApp = angular.module ('myApp', ['fb']);

myApp.config (['facebookProvider', function (facebookProvider) {
    facebookProvider.init ({appId: "714039208665351"});
}]);

// myApp.run (['$rootScope', 'facebook', function ($rootScope, facebook) {
//     $rootScope.$on ('fb.auth.authResponseChange', function (event, response) {
//         if (response == 'connected') {
//             facebook.api ('me').then (function (result) {
//                 $rootScope.userInfo = result;
//             });
//         } else {
//                 $rootScope.userInfo = null;
//         }
//     }
//     facebook.login ();
// }]);