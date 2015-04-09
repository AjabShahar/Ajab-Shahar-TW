angular.module("word", ['ngRoute', 'htmlGenerator', 'headerModule', 'animationModule', 'popupSupport','utilities'])
.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when("/detail/:wordId",{
            templateUrl:"/user/js/words/partials/wordDetails.html",
            controller:"wordDetailsController"
        })
        .when('/allWords', {
            templateUrl: "/user/js/words/partials/allWords.html",
            controller: "allWordsController"
        })
        .when('/glossary', {
            templateUrl: "/user/js/words/partials/glossary.html",
            controller: "glossaryController"
        })
        .otherwise( {
            templateUrl: "/user/js/words/partials/allWords.html",
            controller: "allWordsController"
        });

}]);