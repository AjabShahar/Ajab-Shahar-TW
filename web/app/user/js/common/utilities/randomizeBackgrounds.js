angular.module("utilities").directive("ngRandomClass", function () {
    return {
        restrict: 'EA',
        replace: false,
        scope: {
            ngClasses: "="
        },
        link: function (scope, elem, attr) {            

            elem.addClass(scope.ngClasses[Math.floor(Math.random() * (scope.ngClasses.length))]);
        }
    }
});