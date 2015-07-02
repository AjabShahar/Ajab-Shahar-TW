angular.module("word", ['ngRoute', 'htmlGenerator', 'headerModule', 'animationModule', 'popupSupport','utilities', 'thumbnailModule','mediaPlayer'])
.config(["$routeProvider","$locationProvider", function ($routeProvider,$locationProvider) {
    $routeProvider
        .when("/details/:id/:title",{
            templateUrl:"/user/js/words/partials/wordDetails.html",
            controller:"wordDetailsController"
        })
        .when('/allWords', {
            templateUrl: "/user/js/words/partials/featuredContent.html",
            controller: "wordFeaturedContentController"
        })
        .when('/glossary', {
            templateUrl: "/user/js/words/partials/glossary.html",
            controller: "glossaryController"
        })
        .otherwise( {
            templateUrl: "/user/js/words/partials/featuredContent.html",
            controller: "wordFeaturedContentController"
        });

        $locationProvider.html5Mode(true);
}]);