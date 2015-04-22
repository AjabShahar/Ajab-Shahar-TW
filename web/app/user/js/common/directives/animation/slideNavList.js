animationModule.animation('.slide-nav-list', function () {
    return {
        beforeAddClass: function (element, className, done) {
            if (className == 'ng-hide') {
                element.removeClass("show");
                $(".collapse").addClass("expand");
            }
            else {
                done();
            }
        },
        beforeRemoveClass: function (element, className, done) {
            if (className == 'ng-hide') {
                element.addClass("show");
                $(".collapse").removeClass("expand");
                var windowHeight = $(document).height();// FF issue
                element.height(windowHeight);
                $('second-layer-list').css('height', windowHeight);
                $('overlay').css('height', windowHeight);
            }
            else {
                done();
            }
        }
    };
});
