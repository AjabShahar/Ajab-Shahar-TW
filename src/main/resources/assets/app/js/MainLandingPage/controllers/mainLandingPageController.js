var mainLandingPageController = function($scope){
$scope.url='O-WVDBpBdRY';
$scope.myHTML =
'<song-with-details overlay-id="overlayId1" custom-style="shift4" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Practice the art of dying!" singer="Parvathy Baul" category-name="Song & Reflection" duration="09:11" poet="Sharath"></song-with-details>'+
'<film-with-details overlay-id="overlayId2" custom-style="shift5" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" context="Prahlad Tipanya Meets His Guru" name="Koi Sunta Hai"></film-with-details>'+
'<reflection-with-details overlay-id="overlayId3" custom-style="shift6" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="The Ulatbansi of Kabir" by="Linda Hess"></reflection-with-details>'+
'<song-with-details overlay-id="overlayId4" custom-style="shift1" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Practice the art of dying!" singer="PARVATHY BAUL"></song-with-details>'+
'<unknown-format overlay-id="overlayId5" custom-style="shift2" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Parrot & The Jungle Fire" description="Dance drama by children of HBP School"></unknown-format>'+
'<unknown-format overlay-id="overlayId6" custom-style="shift3" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Kahat Kabir" description="Paintings by GHULAM SHEIKH"></unknown-format>'+
'<word-with-details overlay-id="overlayId7" custom-style="shift4" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Untellable Tale" contextual-meaning="An iconic poetic phrase in Kabir, which evokes a realm of experience beyond description..."></word-with-details>'+
'<unknown-format overlay-id="overlayId8" custom-style="shift5" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="10 Images X 100 Words" description="Photo Essay by SMRITI CHANCHANI & VIPUL RIKHI"></unknown-format>'+
'<song-with-details overlay-id="overlayId9" custom-style="shift6" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Everyday I Wish You Well" singer="MUKHTIYAR ALI"></song-with-details>'+
'<relection-with-details overlay-id="overlayId10" custom-style="shift1" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Reinterpreting Kabir’s life & times" by="PURUSHOTTAM AGRAWAL"></relection-with-details>'+
'<gathering-with-details overlay-id="overlayId11" custom-style="shift2" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Bangalore Festival Of Kabir" location="Bangalore" date="2009"></gathering-with-details>'+
'<couplet-with-details overlay-id="overlayId12" custom-style="shift3" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="A pot in water, water in a pot..."></couplet-with-details>'+
'<couplet-with-details overlay-id="overlayId13" custom-style="shift4" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="A pot in water, water in a pot..."></couplet-with-details>'+
'<gathering-with-details overlay-id="overlayId14" custom-style="shift5" img-src="http://phpalbum.net/demo4/main.php?cmd=imageorig&var1=IMGP7051a.JPG" name="Evening With Madan Gopal Singh" location="Bangalore" date="2011"></gathering-with-details>';

}

mainLandingPageApp.controller('mainLandingPageController',['$scope',mainLandingPageController]);
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