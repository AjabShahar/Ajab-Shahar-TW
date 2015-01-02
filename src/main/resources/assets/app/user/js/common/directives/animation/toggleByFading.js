animationModule.animation('.toggleByFading', function () {
  return {
    beforeAddClass: function (element, className, done) {
      if (className == 'ng-hide') {
        element.fadeOut(800, function() {
            $(this).addClass('ng-hide');
        });
      }
      else {
             done();
           }
      },
      beforeRemoveClass: function (element, className, done) {
        if (className == 'ng-hide') {
              element.fadeIn(800, function() {
                  $(this).removeClass('ng-hide');
              });
          }
          else {
                 done();
          }
        }
      };
});