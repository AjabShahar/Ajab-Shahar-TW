"use strict";

angular.module("people").controller("peopleExploreController", ['$scope','$route','$q','$window','peopleService', function ($scope, $route,$q,$window, peopleService) {

    $scope.classes = ['hansas', 'sadhus', 'yoginis'];

    $scope.relatedData = [];
    $scope.dynamicList = [];
    $scope.allRelatedData = [];

    var sortService = function(firstItem, secondItem){
        if(firstItem.type === 'reflection' && secondItem.type != 'reflection'){
            return firstItem.englishTitle.localeCompare(secondItem.translitTitle);
        }
        else if(firstItem.type != 'reflection' && secondItem.type === 'reflection'){
            return firstItem.translitTitle.localeCompare(secondItem.englishTitle);
        }
        else if(firstItem.type != 'reflection' && secondItem.type != 'reflection'){
            return firstItem.translitTitle.localeCompare(secondItem.translitTitle);
        }
        return firstItem.englishTitle.localeCompare(secondItem.englishTitle);
    };

    $scope.init = function(){
        $scope.person = {};
        var personId = $route.current.params.id;

        var songPromise = peopleService.getSongs(personId);
        var reflectionPromise = peopleService.getReflections(personId);
        var wordPromise = peopleService.getWords(personId);

        var promises = [songPromise, reflectionPromise, wordPromise];

        peopleService.getPerson(personId).then(function(response){
            $scope.person = new AjabShahar.peopleModel(response.data);
        });

        $q.all(promises).then(function(data){
            var songs = data[0].data.songs;
            songs.length > 0 ? $scope.dynamicList.push({name:"songs",selected:false}) : '';

            _.each(songs, function(song){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(song,"song"));
            });

            var reflections = data[1].data;
            reflections.length > 0 ? $scope.dynamicList.push({name:"reflections",selected:false}) : '';

            _.each(reflections, function(reflection){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(reflection,"reflection"));
            });

            var words = data[2].data;
            words.length > 0 ? $scope.dynamicList.push({name:"words",selected:false}):'';
            _.each(words, function(word){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(word,"word"));
            });
            $scope.relatedData = $scope.relatedData.sort(sortService);
            $scope.dynamicList.push({name:"all",selected:true});
            $scope.allRelatedData = angular.copy($scope.relatedData);
        });
    };

    $scope.applyFilter = function(criteria){
        if(criteria === 'all')
          $scope.relatedData = angular.copy($scope.allRelatedData);
        else{
            $scope.relatedData = _.filter($scope.allRelatedData,function(item){
                return criteria.indexOf(item.type) === 0;
            })
        }

        _.each($scope.dynamicList,function(item){
            item.selected = item.name === criteria
        })
    };

    $scope.selectThumbnail = function(thumbnail){
        $window.location.href = thumbnail.getUrl();
    };

    $scope.init();

}]);
