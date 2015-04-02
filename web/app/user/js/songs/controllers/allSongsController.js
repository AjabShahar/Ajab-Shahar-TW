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
    var contentTextRepresentation = 'Transliteration';

    $scope.$on("contentTextRepresentation",function(event,data){
        contentTextRepresentation =data;
        sortSongsList($scope.filteredSongList);
    });

    var sortSongsList = function(songs){
        var sortFunction = contentTextRepresentation === 'Transliteration'? compareTranslitTitles:compareEnglishTitles;
        songs.sort(sortFunction);
    };

    var compareEnglishTitles = function(firstSong,secondSong){
        return firstSong.englishTranslation.localeCompare(secondSong.englishTranslation);
    };

    var compareTranslitTitles = function (firstSong, secondSong){
        return firstSong.englishTransliteration.localeCompare(secondSong.englishTransliteration);
    };

    var filterAndLoad = function(songs){
        $scope.closeSecondParda();
        $scope.filteredSongList = sieve.filter(songs);
        loadFilterItemsFrom($scope.filteredSongList);
    };

    $scope.filterCategoryClicked = function(criteria){
        //$scope.openSecondParda = false;
        //$scope.selectedFilterCategory.active = false;
        $scope.closeSecondParda();
        $scope.selectedFilterCategory = criteria;

        if(!criteria.disabled && _.isEmpty(criteria.value)){
            $scope.openSecondParda = true;
            criteria.active = true;
        }
    };

    $scope.removeFilterCriteria = function(criteria){
        sieve.removeFilterCriteria(criteria.name);
        filterAndLoad(songs);
    };

    $scope.clearAllFilters = function(){
        sieve.clearFiltersWithDisplayName();
        filterAndLoad(songs);
    };

    $scope.filterItemSelected = function(filterValue){
        sieve.setFilterCriteria($scope.selectedFilterCategory.name,filterValue);
        filterAndLoad(songs);
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
            filterAndLoad(songs);
        }
    };

    $scope.clearAlphabetFilters = function(){
        sieve.removeFilterCriteria("englishTransliteration");
        sieve.removeFilterCriteria("englishTranslation");
        filterAndLoad(songs);
    };

    $scope.toggleExpandFilter = function(){
        $scope.expandFilter = !$scope.expandFilter;
    };

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            songs = songMapper.getThumbnails(songsList.data.songs);
            $scope.filteredSongList = songs || [];
            loadFilterItemsFrom(songs);
            sortSongsList($scope.filteredSongList);
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
