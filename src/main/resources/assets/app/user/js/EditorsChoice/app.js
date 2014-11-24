var editorsChoiceApp = angular.module('editorsChoiceApp',['angular-parallax','thumbnailModule','mediaPlayer','popupSupport','htmlGenerator','sticky','headerModule', 'djds4rce.angular-socialshare']);

editorsChoiceApp.config(resourceUrlWhiteList);
editorsChoiceApp.factory('contentService', ['$http', contentService]);

editorsChoiceApp.run(function($FB){
  $FB.init('714039208665351');
});