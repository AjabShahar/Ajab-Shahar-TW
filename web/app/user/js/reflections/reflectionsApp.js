angular.module('reflection', ['ngRoute','htmlGenerator','thumbnailModule','mediaPlayer','utilities','animationModule','headerModule', 'filterModule'])
    .config(["$routeProvider","$locationProvider", function ($routeProvider,$locationProvider) {
        $routeProvider
            .when("/details/:id",{
                templateUrl:"/user/js/reflections/partials/reflectionDetails.html",
                controller:"reflectionDetailsController"
            })
            .when('/all', {
                templateUrl: "/user/js/reflections/partials/allReflections.html",
                controller: "allReflectionsController"
            })
            .otherwise( {
                templateUrl: "/user/js/reflections/partials/allReflections.html",
                controller: "allReflectionsController"
            });
        $locationProvider.html5Mode(true);
    }]);