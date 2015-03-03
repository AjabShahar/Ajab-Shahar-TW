
ajabShaharScrollBar.directive('customScrollBar',function(){
    return {
        restrict:'AE',
        link: function (scope, elm, attrs) {
            Ps.initialize(document.getElementById('scrollPaneContainer'));
        }
    };
});
