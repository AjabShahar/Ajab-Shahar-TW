angular.module("reflection").controller('allReflectionsController', ['$scope', 'reflectionsContentService', '$window','$rootScope', function ($scope, reflectionsContentService, $window,$rootScope) {
    $scope.reflections = [];

    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.reflectionCount = 0;

    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.expandFilter = false;
    $scope.filterItems = {};
    $scope.selectedFilterCategory = {};
    $scope.openSecondParda = false;
    $rootScope.isGridPage = true;

    var i = 0;

    $scope.toggleExpandFilter = function () {
        $scope.expandFilter = !$scope.expandFilter;
        if (!$scope.expandFilter) {
            $scope.closeSecondParda()
        }
    };

    $scope.closeSecondParda = function () {
        if ($scope.openSecondParda) {
            $scope.openSecondParda = false;
        }
        $scope.selectedFilterCategory.active = false;
    };

    $scope.openParda = function () {
        $scope.openSecondParda = true;
    };

    $scope.shiftThumbnail = function (index) {
        return "shift" + (index + 1);
    };

    $scope.navigateToDetailPage = function (id) {
        $window.location.href = 'details/'+id;
    };

    $scope.init = function () {
        reflectionsContentService.getPublishedReflections().then(function (reflections) {
            var reflectionsList = reflections.data.reflections;
            _.each(reflectionsList, function (reflection, index) {
                $scope.reflections.push(new AjabShahar.ThumbnailObject(reflection, "reflection"));
            });
            $scope.reflectionCount = $scope.reflections.length;
        });
    };

    $scope.init();
}]);
