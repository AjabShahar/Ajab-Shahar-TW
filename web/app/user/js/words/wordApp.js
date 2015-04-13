angular.module("word", ['ngRoute', 'htmlGenerator', 'headerModule', 'animationModule', 'popupSupport','utilities', 'thumbnailModule'])
.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/details/:id",{
            templateUrl:"/user/js/words/partials/wordDetails.html",
            controller:"wordDetailsController"
        })
        .when('/featuredContent', {
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
}]);