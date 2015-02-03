animationModule.animation('.slideNavigation', function () {
    var _slideLeftNav = function(element) {
        $(".slide-nav-list").toggleClass("show");
          element.find(".collapse").toggleClass("expand");
           _setLeftNavHeight();
    }

    var _setLeftNavHeight = function() {
        var windowHeight = $(document).height();
        $('.slide-nav-list').height(windowHeight);
    }

  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        _slideLeftNav(element);
      }
      else {
             done();
           }
      },
      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
            _slideLeftNav(element);
          }
          else {
                 done();
          }
        }
      };
});