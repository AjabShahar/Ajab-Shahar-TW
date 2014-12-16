var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = this;
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

    $scope.toggleVersion = function(){
        $scope.showVersion = !$scope.showVersion;
    }

    $scope.init = function(){
        $scope.url = $location.absUrl();
        $scope.songId = $location.search().id;
        $scope.open($scope.songId);

        songsContentService.getSongsVersions($scope.songId).then(function(result){
            $scope.versions = result.data.songs;
            $scope.numberOfVersions = result.data.songs.length;
            $scope.umbrellaTitleEnglishTransliteration = result.data.songs[0].umbrellaTitleEnglishTransliteration;
            $scope.umbrellaTitleEnglishTranslation = result.data.songs[0].umbrellaTitleEnglishTranslation;
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.songId).then(function(result){
            $scope.renditions = result.data;
        });
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
        var songId = $scope.getSongId(id);
        return $scope.showContentDetails[songId];
    }

    $scope.init();
};

songDetailsApp
.controller('songDetailsController',['$scope','$location','songsContentService',songDetailsController]);