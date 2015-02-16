
angular.module("mainLanding").directive('popup',function(){
    return {
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: {
            show: '=', //true/false - displays popup
            width: '@', //width of the popup
            onClose:'&',
            currentIndex:'=',
            popupCount:'='
        },
        templateUrl: '/mainlanding/common/popup.html',
        link: function(scope,element,attrs) {
            jQuery(element).css("height", jQuery(window).height());

            scope.getPopupCount = function() {
                return scope.popupCount?new Array(parseInt(scope.popupCount)):new Array(15);
            };

            scope.onSelect = function(index){
//                $scope.detailsService.select(parseInt(scope.index),index);
            };

            scope.isActive = function(index){
                return scope.currentIndex == index;
            };

            scope.close = function(){
                scope.onClose();
            };
        }
    };
});
