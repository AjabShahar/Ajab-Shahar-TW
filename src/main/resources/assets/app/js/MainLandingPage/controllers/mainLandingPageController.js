var mainLandingPageController = function($scope,contentService){
    $scope.html = '<test>blah</test>';
    $scope.init = function(){
        var details = contentService.getLandingPageThumbnails().details;
        var result = _.reduce(details, function(memo, value, index){
            shiftIndex = ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
            if(value.category=='Songs'){
                return memo + '<song-with-details overlay-id="oid'+index +
                ' custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' singer="'+details[index].singer+'"' +
                ' category-name="'+details[index].category-name+'"'+
                ' poet="'+details[index].poet+'"></song-with-details>';
            }

            if(value.category=='Films'){
                return memo + '<film-with-details overlay-id="oid'+index +
                ' custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' context="'+details[index].context+'"'+ '></film-with-details>';
            }

            if(value.category=='Reflections'){
                return memo + '<reflection-with-details overlay-id="oid'+index +
                ' custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' by="'+details[index].by+'"></reflection-with-details>';
            }

            if(value.category=='Words'){
                return memo + '<word-with-details overlay-id="oid'+index +
                ' custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' contextual-meaning="'+details[index].contextualMeaning+'">' +
                '</word-with-details>';
            }

            if(value.category=='Gathering'){
                return memo + '<gathering-with-details overlay-id="oid'+index +
                ' custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' location="'+details[index].location+'"' +
                ' date="'+details[index].date+'">'+
                '</gathering-with-details>';
            }

            if(value.category=='Couplets'){
                return memo + '<couplet-with-details overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                '</couplet-with-details>';
            }

        return memo ;
        + '<unknown-format overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'" ' +
                ' img-src="'+details[index].imageUrl+'"'+
                ' name="'+details[index].name+'"'+
                ' description="'+details[index].description+'">'+
                '</unknown-format>'},'');

        $scope.html = result;
    }

    $scope.init();
}

mainLandingPageApp.controller('mainLandingPageController',['$scope','contentService',mainLandingPageController]);
mainLandingPageApp.directive('bindUnsafeHtml', ['$compile', function ($compile) {
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