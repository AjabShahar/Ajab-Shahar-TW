animationModule.animation('.slide-nav-list', function () {
  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        element.removeClass("show");
        $(".collapse").addClass("expand");
        $('.headerArt').removeClass('stackDown');
      }
      else {
             done();
           }
      },
      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
            element.addClass("show");
            $(".collapse").removeClass("expand");
            $('.headerArt').addClass('stackDown');
            var windowHeight = $(document).height();// FF issue
            element.height(windowHeight);
          }
          else {
                 done();
          }
        }
      };
});
