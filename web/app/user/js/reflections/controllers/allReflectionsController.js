angular.module("reflection").controller('allReflectionsController', ['$scope', 'reflectionsContentService', '$window','$rootScope','$filter',
    function ($scope, reflectionsContentService, $window,$rootScope,$filter) {
        var reflections = [];
        $scope.reflectionsList = [];
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

        $scope.criteriaList = AjabShahar.user.ReflectionFilterConfig.filterCategories;
        var filterItemsLoaderConfig = AjabShahar.user.ReflectionFilterConfig.filterItemsLoader;
        var sieve = new AjabShahar.user.Sieve($scope.criteriaList);

        $scope.closeSecondParda = function () {
            if ($scope.openSecondParda) {
                $scope.openSecondParda = false;
            }
            $scope.selectedFilterCategory.active = false;
        };

        $scope.filterCategoryClicked = function (criteria) {
            $scope.closeSecondParda();
            $scope.selectedFilterCategory = criteria;

            if (!criteria.disabled && _.isEmpty(criteria.value)) {
                $scope.openSecondParda = true;
                criteria.active = true;
            }
        };


        var loadFilterItemsFrom = function (reflections) {
            $scope.criteriaList.forEach(function (criterion) {
                if (!_.isEmpty(criterion.displayName)) {
                    var methodToCall = filterItemsLoaderConfig[criterion.displayName];
                    $scope.filterItems[criterion.displayName] = reflectionsContentService[methodToCall](reflections);
                    criterion.empty = !!_.isEmpty($scope.filterItems[criterion.displayName]);
                }
            });
        };

        var updateFilterCategoriesState = function(){
            $scope.criteriaList.forEach(function(criterion){
                criterion.disabled = !!(criterion.value || criterion.empty );
            });
        };


        var filterAndLoad = function (reflections) {
            $scope.closeSecondParda();
            $scope.reflectionsList = sieve.filter(reflections);
            loadFilterItemsFrom($scope.reflectionsList);
            updateFilterCategoriesState();
        };

        $scope.removeFilterCriteria = function (criteria) {
            sieve.removeFilterCriteria(criteria.name);
            filterAndLoad(reflections);
        };


        $scope.clearAllFilters = function () {
            sieve.clearFiltersWithDisplayName();
            filterAndLoad(reflections);
        };

        $scope.filterItemSelected = function (filterValue) {
            sieve.setFilterCriteria($scope.selectedFilterCategory.name, filterValue);
            filterAndLoad(reflections);
        };

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
        reflectionsContentService.getPublishedReflections().then(function (reflectionsList) {
            var allReflections = reflectionsList.data.reflections;
            _.each(allReflections, function (reflection) {
                var reflectionObject = new AjabShahar.ThumbnailObject(reflection, "reflection");
                reflectionObject.words = reflectionObject.actualContent.words;
                reflectionObject.speaker = reflectionObject.actualContent.speaker;
                reflectionObject.people = reflectionObject.actualContent.people;
                $scope.reflectionsList.push(reflectionObject);
            });
            $scope.reflectionCount = $scope.reflectionsList.length;
            $scope.reflectionsList = sortList($scope.reflectionsList,'englishTitle');
            reflections = $scope.reflectionsList || [];
            loadFilterItemsFrom(reflections);
            updateFilterCategoriesState();
        });
    };

    $scope.init();
}]);
