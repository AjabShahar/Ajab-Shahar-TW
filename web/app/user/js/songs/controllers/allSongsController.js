allSongsApp.controller('allSongsController', ['$scope', '$window', 'songsContentService', 'songMapper','sortService', function ($scope, $window, songsContentService, songMapper, sortService) {
    var songs = [];
    $scope.filteredSongList = [];
    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.songCount = 0;
    $scope.expandFilter = false;
    $scope.filterItems = {};
    $scope.selectedFilterCategory = {};
    $scope.openSecondParda = false;
    //------------------

    $scope.criteriaList = AjabShahar.user.SongFilterConfig.filterCategories;
    var filterItemsLoaderConfig = AjabShahar.user.SongFilterConfig.filterItemsLoader;
    var sieve = new AjabShahar.user.Sieve($scope.criteriaList);
    var contentTextRepresentation = 'Transliteration';

    $scope.$watch("contentTextRepresentation", function (value) {
        contentTextRepresentation = value;
        $scope.filteredSongList = sortService.sortList($scope.filteredSongList,value);
    });

    var updateFilterCategoriesState = function(){
        $scope.criteriaList.forEach(function(criterion){
            criterion.disabled = !!(criterion.value || criterion.empty );
        });
    };

    var filterAndLoad = function (songs) {
        $scope.closeSecondParda();
        $scope.filteredSongList = sieve.filter(songs);
        loadFilterItemsFrom($scope.filteredSongList);
        updateFilterCategoriesState();
    };

    $scope.filterCategoryClicked = function (criteria) {
        $scope.closeSecondParda();
        $scope.selectedFilterCategory = criteria;

        if (!criteria.disabled && _.isEmpty(criteria.value)) {
            $scope.openSecondParda = true;
            criteria.active = true;
        }
    };

    $scope.removeFilterCriteria = function (criteria) {
        sieve.removeFilterCriteria(criteria.name);
        filterAndLoad(songs);
    };

    $scope.clearAllFilters = function () {
        sieve.clearFiltersWithDisplayName();
        filterAndLoad(songs);
    };

    $scope.filterItemSelected = function (filterValue) {
        sieve.setFilterCriteria($scope.selectedFilterCategory.name, filterValue);
        filterAndLoad(songs);
    };

    $scope.closeSecondParda = function () {
        if ($scope.openSecondParda) {
            $scope.openSecondParda = false;
        }
        $scope.selectedFilterCategory.active = false;
    };

    $scope.alphabetFilterClicked = function (alphabetFilter) {
        if (!_.isEmpty(alphabetFilter)) {
            var filterCategoryName = alphabetFilter.contentTextRepresentation.toLowerCase() === 'translation' ? "englishTranslation" : "englishTransliteration";
            sieve.setFilterCriteria(filterCategoryName, alphabetFilter.alphabet);
            filterAndLoad(songs);
        }
    };

    $scope.clearAlphabetFilters = function () {
        sieve.removeFilterCriteria("englishTransliteration");
        sieve.removeFilterCriteria("englishTranslation");
        filterAndLoad(songs);
    };

    $scope.toggleExpandFilter = function () {
        $scope.expandFilter = !$scope.expandFilter;
        if (!$scope.expandFilter) {
            $scope.closeSecondParda()
        }
    };

    $scope.getAllSongs = function () {
        songsContentService.getAllSongs().then(function (songsList) {
            songs = songMapper.getThumbnails(songsList.data.songs);
            $scope.filteredSongList = songs || [];
            loadFilterItemsFrom(songs);
            $scope.filteredSongList = sortService.sortList($scope.filteredSongList, contentTextRepresentation);
            updateFilterCategoriesState();
        });
    };

    var loadFilterItemsFrom = function (songs) {
        $scope.criteriaList.forEach(function (criterion) {
            if (!_.isEmpty(criterion.displayName)) {
                var methodToCall = filterItemsLoaderConfig[criterion.displayName];
                $scope.filterItems[criterion.displayName] = songsContentService[methodToCall](songs);
                criterion.empty = !!_.isEmpty($scope.filterItems[criterion.displayName]);
            }
        });
    };

    $scope.loadSongFromRange = function () {
        if ($scope.scrollIndex > $scope.filteredSongList.length)
            return;
        $scope.scrollIndex += 12;
    };

    $scope.navigateToSong = function (id) {
        $window.location.href = '/songs/?id=' + id;
    };

    $scope.getAllSongs();
}]);


