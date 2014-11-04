var landingPagesApp = angular.module('landingPagesApp',['angular-parallax','thumbnailModule','mediaPlayer','popupSupport','htmlGenerator','sticky','headerModule']);

landingPagesApp.config(resourceUrlWhiteList);
landingPagesApp.factory('contentService', ['$http', contentService]);

landingPagesApp.directive('bindUnsafeHtml', ['$compile', function ($compile) {
  return function(scope, element, attrs) {
      scope.$watch(
        function(scope) {
          // watch the 'bindUnsafeHtml' expression for changes
          return scope.$eval(attrs.bindUnsafeHtml);
        },
        function(value) {
          // when the 'bindUnsafeHtml' expression changes
          // assign it into the current DOM
          element.html(value);

          // compile the new DOM and link it to the current
          // scope.
          // NOTE: we only compile .childNodes so that
          // we don't get into infinite loop compiling ourselves
          $compile(element.contents())(scope);
        }
    );
};
}]);