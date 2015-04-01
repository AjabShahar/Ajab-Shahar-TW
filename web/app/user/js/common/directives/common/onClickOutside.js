angular.module("filterModule").directive("onClickOutside", ["$document", function ($document) {
    return {
        link: function ($scope, $element, $attrs) {
            var scopeExpression = $attrs.onClickOutside,
                onDocumentClick = function (event) {
                    var isChild = $element.find(event.target).length > 0;

                    if (!isChild) {
                        $scope.$apply(scopeExpression);
                    }
                };

            $document.on("click", onDocumentClick);

            $element.on('$destroy', function () {
                $document.off("click", onDocumentClick);
            });
        }
    }
}]);