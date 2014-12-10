var allSongsController = function($scope,songsContentService,popupService,$filter){
    $scope.songs=[];
    $scope.allSongs = null;
    $scope.totalFilteredSongs = 0;
    $scope.detailsService = popupService;
    $scope.activeLetter = '';
    $scope.scrollIndex = 9;
    $scope.singerNameInFilter = '';
    $scope.poetNameInFilter = '';
    $scope.singers = [];
    $scope.poets = [];
    $scope.shouldShowSingers = false;
    $scope.shouldShowPoets = false;

    var i = 0;

    $scope.filterSingers = function(){
        var songStartsWithResult = _.filter($scope.songs,$scope.songStartsWithComparator);
        var songsWithPoetNameResult = $filter('songFilterByPoet')(songStartsWithResult, $scope.poetNameInFilter);

        $scope.singers.splice(0, $scope.singers.length);
        $scope.singers.push('');
        _.each(songsWithPoetNameResult,function(song){
            _.each(song.singers, function(singer){
                if(_.findWhere($scope.singers,{name:singer.name})==null)
                    $scope.singers.push(singer);
            });
        });
    }

    $scope.removeSingerFilter = function(){
        $scope.singerNameInFilter = '';
    }

    $scope.removePoetFilter = function(){
        $scope.poetNameInFilter = '';
    }

    $scope.showSingers = function(){
        $scope.filterSingers();
        $scope.shouldShowSingers = true;
    }

    $scope.hideSingers = function(){
        $scope.shouldShowSingers = false;
    }

    $scope.showPoets = function(){
        $scope.filterPoets();
        $scope.shouldShowPoets = true;
    }

    $scope.hidePoets = function(){
        $scope.shouldShowPoets = false;
    }

    $scope.$watch('singerNameInFilter',function(newValue,oldValue){
        if(newValue==oldValue)
            return;
        $scope.hideSingers();
    });

    $scope.$watch('poetNameInFilter',function(newValue,oldValue){
        if(newValue==oldValue)
            return;
        $scope.hidePoets();
    });

    $scope.filterPoets = function(){
        var songStartsWithResult = _.filter($scope.songs,$scope.songStartsWithComparator);
        var songsWithPoetNameResult = $filter('songFilterBySinger')(songStartsWithResult, $scope.singerNameInFilter);

        $scope.poets.splice(0, $scope.poets.length);
        $scope.poets.push('');
        _.each(songsWithPoetNameResult,function(song){
            _.each(song.poet, function(poet){
                if(_.findWhere($scope.poets,{name:poet.name})==null)
                    $scope.poets.push(poet);
            });
        });
    }

    $scope.clearFilters = function(){
        $scope.activeLetter = '';
        $scope.singerNameInFilter = '';
        $scope.poetNameInFilter = '';
    }

    $scope.getAllSongs = function(){
        songsContentService.getAllSongs().then(function(songsList){
            $scope.songs = songsList.data.songs;
            $scope.totalFilteredSongs = songsList.data.songs.length;
        });
    }

    $scope.strStartsWith = function(str, prefix) {
        return (str+"").indexOf(prefix) === 0;
    }

    $scope.songStartsWithComparator = function (actual, expected) {
       if (!$scope.activeLetter && $scope.activeLetter=='') {
          return true;
       }
       return $scope.strStartsWith(actual.englishTransliterationTitle,$scope.activeLetter.toUpperCase());
    };

    $scope.open = function(id){
        var songId = $scope.getSongId(id);
        $window.location.href = '/user/partials/songs/details.html?id='+songId;
    }

    $scope.more = function(){
        $scope.scrollIndex += 9;
    }

    $scope.loadSongFromRange = function(){
//        if(i <= $scope.totalFilteredSongs){
//            songsContentService.getSongsInRangeAndFilteredBy(i, $scope.activeLetter).then(function(result){
//                for(j=0; j< result.data.songs.length; j++){
//                    $scope.songs.push(result.data.songs[j]);
//                }
//
//                i += 9;
//            });
//        }
    }

    $scope.getAllSongs();
};

allSongsApp.controller('allSongsController',['$scope','songsContentService','popupService','$filter',allSongsController]);