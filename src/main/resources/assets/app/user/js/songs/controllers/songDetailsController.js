var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = $scope;
    $scope.showContentDetails = {};
    $scope.prevId = null;
    $scope.currentIndex = 0; // Initially the index is at the first element
    $scope.direction = '';
    $scope.dropDownVisible = false;
    $scope.showVersion = true;
    $scope.umbrellaTitleEnglishTranslation = '';
    $scope.umbrellaTitleEnglishTransliteration='';
    $scope.numberOfVersions = 0;
    $scope.songText = {"refrainOriginal":'',"refrainEnglishTranslation":'',"refrainEnglishTransliteration":'',songTextContents:[],openingCouplets:[]};
    $scope.song = {};
    $scope.poet = '';
    $scope.englishTranslationVisible = true;
    $scope.originalVisible = false;
    $scope.englishTransliterationVisible = false;

    $scope.toggleVersion = function(){
        $scope.showVersion = !$scope.showVersion;
    }

    $scope.init = function(){
        $scope.url = $location.absUrl();
        $scope.songId = $location.search().id;
        $scope.open($scope.songId);

        songsContentService.getSongsVersions($scope.songId).then(function(result){
            $scope.versions = $scope.getSongsVersions(result.data.songs, $scope.songId);
            $scope.numberOfVersions = result.data.songs.length;
            $scope.umbrellaTitleEnglishTransliteration = result.data.songs[0].umbrellaTitleEnglishTransliteration;
            $scope.umbrellaTitleEnglishTranslation = result.data.songs[0].umbrellaTitleEnglishTranslation;
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.songId).then(function(result){
            $scope.renditions = result.data.songs;
        });
        songsContentService.getSong($scope.songId).then(function(result){
            $scope.poet = result.data.poet[0];
            $scope.song = result.data;
            $scope.songText.refrainEnglishTranslation = result.data.songText.refrainEnglishTranslation;
            $scope.songText.refrainOriginal = result.data.songText.refrainOriginal;
            $scope.songText.refrainEnglishTransliteration = result.data.songText.refrainEnglishTransliteration;
            $scope.getSongsLyrics(result.data.songText.songTextContents,result.data.songText.openingCouplets);
        });

        $scope.getSongsVersions = function(songs, songId){
            for(index in songs){
               if (songs[index].id == songId){
                 var dummyVar = songs[index];
                 songs.splice(index,1);
                 songs.splice(0,0,dummyVar);
               }
            }
            return songs;
        }
    }

    $scope.shouldShowVersions = function(){
        return $scope.numberOfVersions > 1;
    }

    $scope.getSongId = function(id){
        return id.toString().match(/[0-9]+/)[0];
    }

    $scope.open = function(id){
        var songId = $scope.getSongId(id);

        if($scope.prevId!=null)
            $scope.showContentDetails[$scope.prevId] = false;

        $scope.prevId = songId;
        $scope.showContentDetails[songId] = true;
    }

    $scope.isOpen = function(id){
        var songId = $scope.getSongId(id);
        return $scope.showContentDetails[songId];
    }

    $scope.isClosed = function(id){
        return !$scope.isOpen(id);
    }

    $scope.getSongsLyrics = function(songTextComponents, openingCouplets){
        sortedSongTextComponents = _.sortBy(songTextComponents, function(songTextComponent) { return songTextComponent.sequenceNumber;});
        for(index in sortedSongTextComponents){
            var item = sortedSongTextComponents[index];
                $scope.songText.songTextContents.push(item);
        }

        sortedSongTextComponents = _.sortBy(openingCouplets, function(openingCouplet) { return openingCouplet.sequenceNumber;});
        for(index in sortedSongTextComponents){
             var item = sortedSongTextComponents[index];
             $scope.songText.openingCouplets.push(item);
        }
    }

    $scope.showEnglishTranslation = function(){
        $scope.englishTransliterationVisible = false;
        $scope.englishTranslationVisible = true;
        $scope.originalVisible = false;
    }

    $scope.showEnglishTransliteration = function(){
        $scope.englishTransliterationVisible = true;
        $scope.englishTranslationVisible = false;
        $scope.originalVisible = false;
    }

    $scope.showOriginal = function(){
        $scope.englishTransliterationVisible = false;
        $scope.englishTranslationVisible = false;
        $scope.originalVisible = true;
    }

    $scope.init();
};

songDetailsApp
.controller('songDetailsController',['$scope','$location','songsContentService',songDetailsController]);