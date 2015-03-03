//ajabShaharScrollBar.directive("customScrollBar", function () {
//    return {
//        link : function(scope,element,attrs){
//            $(element).jScrollPane({
//                width:'600px',
//                height:'500px',
//                background:'#FF0000'
//            });
//        }
//    }
//     Ps.initialize(document.getElementById('av'));
//});
//angular.module("ajabShaharScrollBar").controller("MyCtrl", function ($scope) {
//    $scope.paneConfig = {
//        verticalDragMinHeight: 100
//    }
//});

ajabShaharScrollBar.directive('customScrollBar',function(){
    return {
        restrict:'AE',
//        transclude: true,
//        template: "<div class='scroll-pane' ng-transclude></div>",
        link: function (scope, elm, attrs) {
//                console.log(elm);
            Ps.initialize(document.getElementById('scrollPaneContainer'));
        }
    };
});
