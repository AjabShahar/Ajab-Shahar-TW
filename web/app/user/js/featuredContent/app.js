var featuredContentApp = angular.module('featuredContentApp', ['thumbnailModule', 'mediaPlayer', 'popupSupport', 'htmlGenerator', 'headerModule', 'djds4rce.angular-socialshare','utilities']);

featuredContentApp.config(resourceUrlWhiteList);

featuredContentApp.run(function ($FB) {
    $FB.init('714039208665351');
});
