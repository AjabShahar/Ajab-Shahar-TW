var featuredContentApp = angular.module('featuredContentApp', ['thumbnailModule', 'mediaPlayer', 'popupSupport', 'htmlGenerator', 'headerModule', 'djds4rce.angular-socialshare','word','utilities']);

featuredContentApp.config(resourceUrlWhiteList);


//featuredContentApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);

featuredContentApp.run(function ($FB) {
    $FB.init('714039208665351');
});
