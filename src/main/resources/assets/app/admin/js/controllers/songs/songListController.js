var songListController = function($scope, contentService,nameService){
    $scope.songs = [];
    $scope.nameService = nameService;
    $scope.init = function(){
        contentService.getAllSongs().then(function(result){
            var allSongs = result.data.songs;
            $scope.songs = _.reduce(allSongs,function(songs, value,index) {
                var toBeAdded={};
                toBeAdded.title = value.englishTranslationTitle;
                toBeAdded.categoryName = value.category;
                toBeAdded.singerNames = _.reduce(value.singers, function(memo, value, index){ return ((index!=0)?' ,':'')+ memo+ value; },'');
                toBeAdded.poetNames = _.reduce(value.poets, function(memo, value, index){ return ((index!=0)?' ,':'')+ memo+ value; },'');
                toBeAdded.id = value.id;
                songs.push(toBeAdded);
                return songs;
            },[])
        });
    }

    $scope.init();
}

adminApp.controller('songListController',['$scope','contentService','nameService',songListController]);