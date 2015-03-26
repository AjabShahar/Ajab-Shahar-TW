animationModule.animation('.toggleBySlide', function () {
  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        element.slideUp(750,function(){
            $('.headerArt').addClass('stackDown');
        });

      }
      else {
             done();
           }
      },

      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
            element.removeClass('ng-hide');
            $(".headerArt").removeClass("stackDown");
            element.slideDown(750);
          }
          else {
                 done();
          }
        }
      };
});
