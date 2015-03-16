animationModule.animation('.slide-nav-list', function () {
  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        element.removeClass("show");
        element.find(".collapse").addClass("expand");
      }
      else {
             done();
           }
      },
      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
            element.addClass("show");
            element.find(".collapse").removeClass("expand");

            var windowHeight = $(document).height();
            element.height(windowHeight);
          }
          else {
                 done();
          }
        }
      };
});
