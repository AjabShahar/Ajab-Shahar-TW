var bindDynamicHtml = function ($compile) {
    return function (scope, element, attrs) {
        scope.$watch(
            function (scope) {
                return scope.$eval(attrs.bindDynamicHtml);
            },
            function (value) {
                element.html(value);
                $compile(element.contents())(scope);
            }
        );
    };
};
