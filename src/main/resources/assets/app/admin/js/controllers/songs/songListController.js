var songListController = function($scope, contentService,nameService){
    $scope.songs = [];
    $scope.nameService = nameService;
    $scope.init = function(){
        contentService.getAllSongs().then(function(result){
            var allSongs = result.data;
            $scope.songs = _.reduce(allSongs,function(songs, value,index) {
                var toBeAdded={};
                toBeAdded.title = value.englishTranslation;
                toBeAdded.categoryName = value.songCategory.name;
                toBeAdded.singerNames = _.reduce(value.singers, function(memo, value, index){ return ((index!=0)?' ,':'')+ memo+ nameService.getName(value); },'');
                toBeAdded.poetNames = _.reduce(value.poets, function(memo, value, index){ return ((index!=0)?' ,':'')+ memo+ nameService.getName(value); },'');
                toBeAdded.id = value.id;
                songs.push(toBeAdded);
                return songs;
            },[])
        });
    }

    $scope.init();
}

adminApp.controller('songListController',['$scope','contentService','nameService',songListController]);