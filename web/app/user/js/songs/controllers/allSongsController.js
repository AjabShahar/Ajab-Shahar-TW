var allSongsController = function($scope,$window,songsContentService,songMapper){
    var songs=[];
    $scope.filteredSongList=[];
    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.songCount = 0;
    $scope.expandFilter = false;
    $scope.filterItems ={};
    $scope.selectedFilterCategory = {};
    $scope.openSecondParda = false;
    //------------------

    $scope.criteriaList = Ajabshahar.user.SongFilterConfig.filterCategories;
    var filterItemsLoaderConfig = Ajabshahar.user.SongFilterConfig.filterItemsLoader;
    var sieve = new Ajabshahar.user.Sieve($scope.criteriaList);

    $scope.filterCategoryClicked = function(criteria){
        //var methodToCall = filterItemsLoaderConfig[criteria.displayName];
        //$scope.filterItems =songsContentService[methodToCall]($scope.filteredSongList);
        $scope.selectedFilterCategory.active = false;
        $scope.selectedFilterCategory = criteria;
        if(!criteria.disabled && _.isEmpty(criteria.value)){
            $scope.openSecondParda = true;
            criteria.active = true;
        }
    };

    $scope.removeFilterCriteria = function(criteria){
        sieve.removeFilterCriteria(criteria.name);
        $scope.filteredSongList = sieve.filter(songs);
        loadFilterItemsFrom($scope.filteredSongList);
    };

    $scope.clearAllFilters = function(){
        sieve.clearFiltersWithDisplayName();
        $scope.filteredSongList = sieve.filter(songs);
        $scope.closeSecondParda();
        loadFilterItemsFrom($scope.filteredSongList);
    };

    $scope.filterItemSelected = function(filterValue){
        sieve.setFilterCriteria($scope.selectedFilterCategory.name,filterValue);
        $scope.filteredSongList = sieve.filter(songs);
        $scope.closeSecondParda();
        loadFilterItemsFrom($scope.filteredSongList);
    };

    $scope.closeSecondParda = function(){
        if($scope.openSecondParda){
            $scope.openSecondParda = false;
        }
        $scope.selectedFilterCategory.active =false;
    };

    $scope.alphabetFilterClicked = function(alphabetFilter){
        if(!_.isEmpty(alphabetFilter)){
            var filterCategoryName = alphabetFilter.contentTextRepresentation.toLowerCase() === 'translation'? "englishTranslation":"englishTransliteration";
            sieve.setFilterCriteria(filterCategoryName,alphabetFilter.alphabet);
            $scope.filteredSongList =sieve.filter(songs);
            loadFilterItemsFrom($scope.filteredSongList);
        }
    };

    $scope.clearAlphabetFilters = function(){
        sieve.removeFilterCriteria("englishTransliteration");
        sieve.removeFilterCriteria("englishTranslation");
        $scope.filteredSongList = sieve.filter(songs);
        loadFilterItemsFrom($scope.filteredSongList);
    };

    $scope.toggleExpandFilter = function(){
        $scope.expandFilter = !$scope.expandFilter;
    };

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            songs = songMapper.getThumbnails(songsList.data.songs);
            $scope.filteredSongList = songs || [];
            //$scope.songCount = songsList.data.songs.length;
            loadFilterItemsFrom(songs);
        });
    };

    var loadFilterItemsFrom = function(songs){
        $scope.criteriaList.forEach(function(criterion){
            if(!_.isEmpty(criterion.displayName)){
                var methodToCall = filterItemsLoaderConfig[criterion.displayName];
                $scope.filterItems[criterion.displayName] = songsContentService[methodToCall]($scope.filteredSongList);
                criterion.disabled = !!_.isEmpty($scope.filterItems[criterion.displayName]);
            }
        });
    };

 /*   $scope.songStartsWithComparator = function (actual, expected) {
       if (!$scope.activeLetter && $scope.activeLetter=='') {
          return true;
       }
       return $scope.strStartsWith(actual.englishTransliterationTitle.toUpperCase(),$scope.activeLetter.toUpperCase());
    };*/

    $scope.loadSongFromRange = function(){
        if($scope.scrollIndex>$scope.filteredSongList.length)
            return;
        $scope.scrollIndex += 12;
    };

    $scope.navigateToSong = function(id){
        $window.location.href = '/user/partials/songs/details.html?id='+id;
    };

    $scope.getAllSongs();
};

allSongsApp.controller('allSongsController',['$scope','$window','songsContentService','songMapper',allSongsController]);
