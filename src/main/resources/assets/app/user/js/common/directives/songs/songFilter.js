'use strict';

filterModule.directive("songFilter", function() {
    return {
        restrict: 'E',
        scope: {
            songs:'=',
            songCount: '=',
            activeLetter: '=',
            poetNameInFilter : '=',
            singerNameInFilter : '=',
            onClearFilter : '&',
        },
        templateUrl:'/user/js/common/templates/songs/songFilter.html',
        controller: function($scope,$filter) {
            $scope.singers = [];
            $scope.poets = [];
            $scope.shouldShowPoets = false;
            $scope.shouldShowSingers = false;

            $scope.$watch('poetNameInFilter',function(newValue,oldValue){
                if(newValue==oldValue)
                    return;
                $scope.hidePoets();
                $scope.countSongs();
            });

            $scope.$watch('activeLetter',function(newValue,oldValue){
                if(newValue==oldValue)
                    return;
                $scope.countSongs();
            });

            $scope.filterPoets = function(){
                var songsWithTitleResult = $filter('filterSongByTitle')($scope.songs, $scope.activeLetter);
                var songsWithPoetNameResult = $filter('filterSongBySinger')(songsWithTitleResult, $scope.singerNameInFilter);

                $scope.poets.splice(0, $scope.poets.length);
                $scope.poets.push({name:''});
                _.each(songsWithPoetNameResult,function(song){
                    _.each(song.poet, function(poet){
                        if(_.findWhere($scope.poets,{name:poet.name})==null)
                            $scope.poets.push(poet);
                    });
                });
            }

            $scope.clearFilters = function(){
                $scope.singerNameInFilter = '';
                $scope.poetNameInFilter = '';
                $scope.activeLetter = '';
            }

            $scope.countSongs = function(){
                var filterSongByTitleResult = $filter('filterSongByTitle')($scope.songs, $scope.activeLetter);
                var filterSongBySingerResult = $filter('filterSongBySinger')(filterSongByTitleResult, $scope.singerNameInFilter);
                var filterSongByPoetResult = $filter('filterSongByPoet')(filterSongBySingerResult, $scope.poetNameInFilter);

                $scope.songCount = filterSongByPoetResult.length;
            }

            $scope.filterSingers = function(){
                var songsWithTitleResult = $filter('filterSongByTitle')($scope.songs, $scope.activeLetter);
                var songsWithSongNameResult = $filter('filterSongByPoet')(songsWithTitleResult, $scope.poetNameInFilter);

                $scope.singers.splice(0, $scope.singers.length);
                $scope.singers.push({name:''});
                _.each(songsWithSongNameResult,function(song){
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
                $scope.countSongs();
            });

            $scope.showSingers = function(){
                $scope.filterSingers();
                $scope.shouldShowSingers = true;
            }

            $scope.hideSingers = function(){
                $scope.shouldShowSingers = false;
            }
        }
    }
});