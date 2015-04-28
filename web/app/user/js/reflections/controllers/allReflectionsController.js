var allReflectionsController = function ($scope) {
    $scope.reflections = [];
    $scope.allReflections = null;
    $scope.totalFilteredReflections = 0;

    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.reflectionCount = 0;
    $scope.reflectionsList = [];


    $scope.filteredSongList = [];
    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.songCount = 0;
    $scope.expandFilter = false;
    $scope.filterItems = {};
    $scope.selectedFilterCategory = {};
    $scope.openSecondParda = false;


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
    }




};

allReflectionsApp.controller('allReflectionsController', ['$scope', allReflectionsController]);
