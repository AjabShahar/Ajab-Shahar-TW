animationModule.animation('.toggleByFading', function () {
  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        element.fadeOut("fast");
      }
      else {
             done();
           }
      },
      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
            element.removeClass('ng-hide');
            element.fadeIn(2000);
          }
          else {
                 done();
          }
        }
      };
});