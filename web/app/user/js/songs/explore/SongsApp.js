angular.module("song", ["thumbnailModule", "utilities", "ngRoute", "headerModule","wu.masonry"])
    .config(["$routeProvider", "$locationProvider", function ($routeProvider, $locationProvider) {
        $routeProvider
            .when("/:songId/:title", {
                templateUrl: "/user/js/songs/partials/songExplore.html",
                controller: "songExploreController"
            });

        $locationProvider.html5Mode(true);
    }]);
angular.module("song").factory('songsContentService', ['$http', '$q', songsContentService]);
