angular.module('reflection', ['ngRoute','htmlGenerator', 'headerModule'])
    .config(["$routeProvider", function ($routeProvider) {
        $routeProvider
            .when("/details/:id",{
                templateUrl:"/user/js/reflections/partials/reflectionDetails.html",
                controller:"reflectionDetailsController"
            })
            .when('/allReflections', {
                templateUrl: "/user/js/reflections/partials/allReflections.html",
                controller: "allReflectionsController"
            })
            .otherwise( {
                templateUrl: "/user/js/reflections/partials/allReflections.html",
                controller: "allReflectionsController"
            })
    }]);