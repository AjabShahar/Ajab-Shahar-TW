'use strict';

filterModule.directive("songFilter", function() {
    return {
        restrict: 'E',
        replace:true,
        scope: {
            songs:'=',
            songCount: '=',
            activeLetter: '=',
            poetNameInFilter : '=',
            singerNameInFilter : '=',
            expandFilter: '=',
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

            $scope.$watch('singerNameInFilter',function(newValue,oldValue){
                if(newValue==oldValue)
                    return;
                $scope.hideSingers();
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
                _.each(songsWithPoetNameResult,function(song){
                    _.each(song.searchableCriteria.poets, function(poet){
                        if(_.findWhere($scope.poets,{name:poet.name})==null)
                            $scope.poets.push(poet);
                    });
                });
            }

            $scope.filterSingers = function(){
                var songsWithTitleResult = $filter('filterSongByTitle')($scope.songs, $scope.activeLetter);
                var songsWithSongNameResult = $filter('filterSongByPoet')(songsWithTitleResult, $scope.poetNameInFilter);

                $scope.singers.splice(0, $scope.singers.length);
                _.each(songsWithSongNameResult,function(song){
                    _.each(song.searchableCriteria.singers, function(singer){
                        if(_.findWhere($scope.singers,{name:singer.name})==null)
                            $scope.singers.push(singer);
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

            $scope.showSingers = function(){
                $scope.filterSingers();
                $scope.shouldShowSingers = true;
            }

            $scope.toggleSingers = function(){
                if($scope.shouldShowSingers)
                 {
                    $scope.shouldShowSingers = false;
                    return;
                 }
                  else {
                    $scope.shouldShowSingers = true;
                    $scope.shouldShowPoets = false;
                    $scope.filterSingers();
                 }
            }

            $scope.togglePoets = function(){
               if($scope.shouldShowPoets){
                   $scope.shouldShowPoets = false;
               }
               else{
                   $scope.shouldShowPoets = true;
                   $scope.shouldShowSingers = false;
                   $scope.filterPoets();
               }
            }

            $scope.hideSingers = function(){
                $scope.shouldShowSingers = false;
            }

            $scope.initialiseSingerNameInFilter = function(singer){
                $scope.singerNameInFilter = singer;
            }

            $scope.initialisePoetNameInFilter = function(poet){
                $scope.poetNameInFilter = poet;
            }
        }
    }
});
