var allSongsController = function($scope,$window,songsContentService,songMapper){
    var songs=[];
    $scope.filteredSongList=[];
    $scope.activeLetter = '';
    $scope.scrollIndex = 12;
    $scope.songCount = 0;
    $scope.expandFilter = false;
    $scope.filterItems =[];
    var filterCategoryClicked = {};
    $scope.openSecondParda = false;
    //------------------

    $scope.criteriaList = Ajabshahar.user.SongFilterConfig.filterCategories;
    var filterItemsLoaderConfig = Ajabshahar.user.SongFilterConfig.filterItemsLoader;
    var sieve = new Ajabshahar.user.Sieve($scope.criteriaList);

    $scope.filterCategoryClicked = function(criteria){
        var methodToCall = filterItemsLoaderConfig[criteria.displayName];
        $scope.filterItems =songsContentService[methodToCall]($scope.filteredSongList);
        filterCategoryClicked = criteria;
        $scope.openSecondParda = true;
    };

    $scope.removeFilterCriteria = function(criteria){
        sieve.removeFilterCriteria(criteria.name);
        $scope.filteredSongList = sieve.filter(songs)
    };

    $scope.clearAllFilters = function(){
        sieve.clearFiltersWithDisplayName();
        $scope.filteredSongList = sieve.filter(songs);
    };

    $scope.filterItemSelected = function(filterValue){
        sieve.setFilterCriteria(filterCategoryClicked.name,filterValue);
        $scope.filteredSongList = sieve.filter(songs);
        $scope.closeSecondParda();
    };

    $scope.closeSecondParda = function(){
        if($scope.openSecondParda){
            $scope.openSecondParda = false;
        }
    };

    $scope.alphabetFilterClicked = function(alphabetFilter){
        if(!_.isEmpty(alphabetFilter)){
            var filterCategoryName = alphabetFilter.contentTextRepresentation.toLowerCase() === 'translation'? "englishTranslation":"englishTransliteration";
            sieve.setFilterCriteria(filterCategoryName,alphabetFilter.alphabet);
            $scope.filteredSongList =sieve.filter(songs);
        }
    };

    $scope.clearAlphabetFilters = function(){
        sieve.removeFilterCriteria("englishTransliteration");
        sieve.removeFilterCriteria("englishTranslation");
        $scope.filteredSongList = sieve.filter(songs);
    };

    $scope.toggleExpandFilter = function(){
        $scope.expandFilter = !$scope.expandFilter;
    };

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            songs = songMapper.getThumbnails(songsList.data.songs);
            $scope.filteredSongList = songs || [];
            $scope.songCount = songsList.data.songs.length;
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
