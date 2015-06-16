"use strict";

angular.module("people").controller("peopleExploreController", ['$scope','$route','$q','$window','peopleService', function ($scope, $route,$q,$window, peopleService) {

    $scope.classes = ['hansas', 'sadhus', 'yoginis'];

    $scope.relatedData = [];
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

            _.each(songs, function(song){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(song,"song"));
            });

            var reflections = data[1].data;

            _.each(reflections, function(reflection){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(reflection,"reflection"));
            });

            var words = data[2].data;

            _.each(words, function(word){
                $scope.relatedData.push(new AjabShahar.ThumbnailObject(word,"word"));
            });
        });

    };

    $scope.selectThumbnail = function(thumbnail){
        $window.location.href = thumbnail.getUrl();
    };

    $scope.init();

}]);
