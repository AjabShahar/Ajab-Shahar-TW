'use strict';
reflectionsAdminApp.controller('reflectionDetailsController', ['$scope', '$window', '$location', 'reflectionContentService', "loginVerifyService","$q",
    function ($scope, $window, $location, reflectionContentService, loginVerifyService,$q) {
        loginVerifyService.redirectIfNotAuthenticated();
        $scope.reflection = {"transcripts": [], "speaker": {}};
        $scope.people = [];
        $scope.words = [];
        var urlId = $location.search().id;


        var init = function () {
            var getPeoplePromise = reflectionContentService.getPeople().success(function (data) {
                $scope.people = data;
            });

            var getWordsPromise =reflectionContentService.getWords().success(function(data){
                $scope.words = data.words;
            });

            if (urlId != null && urlId != ''){
                var  getReflectionPromise = reflectionContentService.getRefectionById(urlId).success(function (data) {
                        $scope.reflection = data;
                });
                $q.all([getPeoplePromise,getWordsPromise,getReflectionPromise]).then(function(){
                    $scope.words.forEach(function(word){
                        $scope.reflection.words.forEach(function(selectedWord){
                            if(word.id === selectedWord.id){
                                word.ticked = true;
                            }
                        })
                    })
                });
            }
        };

        $scope.saveData = function () {
            reflectionContentService.saveReflection($scope.reflection).success(function (data) {
                $window.location.href = '/admin/partials/home.html';
            });
        };

        init();
    }]);

