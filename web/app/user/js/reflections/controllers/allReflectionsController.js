angular.module("reflection").controller('allReflectionsController', ['$scope', 'reflectionsContentService', '$window','$rootScope','$filter',
    function ($scope, reflectionsContentService, $window,$rootScope,$filter) {
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

    var sortList = function (list, sortCriteria) {
        return $filter('orderBy')(list, sortCriteria);
    };

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
            _.each(reflectionsList, function (reflection) {
                $scope.reflections.push(new AjabShahar.ThumbnailObject(reflection, "reflection"));
            });
            $scope.reflectionCount = $scope.reflections.length;
            $scope.reflections = sortList($scope.reflections,'englishTitle');
        });
    };

    $scope.init();
}]);
