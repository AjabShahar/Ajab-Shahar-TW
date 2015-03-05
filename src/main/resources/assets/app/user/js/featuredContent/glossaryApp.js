var glossaryApp = angular.module('glossaryApp', ['headerModule'])

glossaryApp.directive('bindDynamicHtml', ['$compile', bindDynamicHtml]);
glossaryApp.factory('contentService', ['$http', contentService]);
