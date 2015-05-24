angular.module("song",["thumbnailModule","utilities","ngRoute"]).config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/:songId",{
            templateUrl:"/user/js/songs/partials/songExplore.html",
            controller:"songExploreController"
        });
}]);
angular.module("song").factory('songsContentService', ['$http','$q',songsContentService]);
