var allSongsController = function($scope,songsContentService,popupService){
    $scope.songs=[];
    $scope.allSongs = null;
    $scope.totalFilteredSongs = 0;
    $scope.detailsService = popupService;
    $scope.activeLetter = '';
    $scope.scrollIndex = 9;
    $scope.singerNameInFilter = '';
    $scope.poetNameInFilter = '';
    $scope.alphabetFilters = [
    {alphabet:'A',isSelected:false},
    {alphabet:'B',isSelected:false},
    {alphabet:'C',isSelected:false},
    {alphabet:'D',isSelected:false},
    {alphabet:'E',isSelected:false},
    {alphabet:'F',isSelected:false},
    {alphabet:'G',isSelected:false},
    {alphabet:'H',isSelected:false},
    {alphabet:'I',isSelected:false},
    {alphabet:'J',isSelected:false},
    {alphabet:'K',isSelected:false},
    {alphabet:'L',isSelected:false},
    {alphabet:'M',isSelected:false},
    {alphabet:'N',isSelected:false},
    {alphabet:'O',isSelected:false},
    {alphabet:'P',isSelected:false},
    {alphabet:'Q',isSelected:false},
    {alphabet:'R',isSelected:false},
    {alphabet:'S',isSelected:false},
    {alphabet:'T',isSelected:false},
    {alphabet:'U',isSelected:false},
    {alphabet:'V',isSelected:false},
    {alphabet:'W',isSelected:false},
    {alphabet:'X',isSelected:false},
    {alphabet:'Y',isSelected:false},
    {alphabet:'Z',isSelected:false}];

    var i = 0;

    $scope.clearFilters = function(){
        $scope.clearAlphabetFilter();
        $scope.activeLetter = '';
        $scope.singerNameInFilter = '';
        $scope.poetNameInFilter = '';
    }

    $scope.isSelected = function(alphabet){
        return alphabet.isSelected ? 'active' : '';
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

    $scope.clearAlphabetFilter = function(){
        _.each($scope.alphabetFilters,function(alphabetFilter){
            alphabetFilter.isSelected = false;
        });
    }

    $scope.filterSongsOnLetter = function(currentAlphabetFilter){
        $scope.clearAlphabetFilter();
        $scope.activeLetter = currentAlphabetFilter.alphabet;
        currentAlphabetFilter.isSelected = true;
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

allSongsApp.controller('allSongsController',['$scope','songsContentService','popupService',allSongsController]);