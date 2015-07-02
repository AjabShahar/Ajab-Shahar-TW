var featuredContentApp = angular.module('featuredContentApp', ['thumbnailModule', 'mediaPlayer', 'popupSupport', 'htmlGenerator', 'headerModule','utilities']);

featuredContentApp.config(resourceUrlWhiteList);

featuredContentApp.config(["$locationProvider", function ($locationProvider) {
    $locationProvider.html5Mode(true);
}]);