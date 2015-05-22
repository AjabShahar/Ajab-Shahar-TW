angular.module("song",["thumbnailModule","utilities","ngRoute"]).config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/:id",{
            templateUrl:"/user/js/songs/partials/songExplore.html",
            controller:"songExploreController"
        });
}]);