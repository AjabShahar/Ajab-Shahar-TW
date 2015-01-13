var editorsChoiceApp = angular.module('editorsChoiceApp',['angular-parallax','thumbnailModule','mediaPlayer','popupSupport','htmlGenerator','headerModule','djds4rce.angular-socialshare']);

editorsChoiceApp.config(resourceUrlWhiteList);
editorsChoiceApp.factory('contentService', ['$http', contentService]);
editorsChoiceApp.factory('songThumbnailMapper', [songThumbnailMapper]);

editorsChoiceApp.run(function($FB){
  $FB.init('714039208665351');
});