angular.module("word").
    controller('wordDetailsController', ['$scope','wordService', '$route',function($scope,wordService, $route){
      /*  $scope.popupService = popupService;
        $scope.detailsService = $scope;
        $scope.wordReflections = {"wordThumbnails": [], "reflections": []};
        $scope.wordReflectionsCompleteInfo = {"wordCompleteInfo": [], "reflectionsCompleteInfo": []};
        $scope.words = [];
        $scope.showVersion = true;
        $scope.numberOfReflections = 0;
        $scope.wordId = '';
        $scope.showReflection = false;
        $scope.currentReflection = {};
        $scope.currentWord = {};
        $scope.showWord = true;

        $scope.init = function () {
            $scope.wordId = $routeParams.id;

            wordService.getVersions($scope.wordId).then(function (result) {
                $scope.words.push(result.data.word);
                $scope.wordReflections.reflections = reflectionMapper.getThumbnails(result.data.reflections);
                $scope.wordReflections.wordThumbnails = wordMapper.getThumbnails($scope.words, '');
                $scope.wordReflectionsCompleteInfo.wordCompleteInfo = wordMapper.getWordCompleteInfo($scope.words);
                $scope.currentWord = $scope.wordReflectionsCompleteInfo.wordCompleteInfo[0];
                $scope.wordReflectionsCompleteInfo.reflectionsCompleteInfo = reflectionMapper.getReflectionsCompleteInfo(result.data.reflections);
                $scope.numberOfReflections = $scope.wordReflections.reflections.length;
            });

        }();

        $scope.openReflection = function (id) {
            $scope.showReflection = true;
            $scope.showWord = false;
            angular.forEach($scope.wordReflectionsCompleteInfo.reflectionsCompleteInfo, function (reflection) {
                if (reflection.id == id)
                    $scope.currentReflection = reflection;
            });

        };

        $scope.openWord = function (id) {
            $scope.showWord = true;
            $scope.showReflection = false;
            angular.forEach($scope.wordReflectionsCompleteInfo.wordCompleteInfo, function (word) {
                if (word.id == id)
                    $scope.currentWord = word;
            });

        };

        $scope.toggleVersion = function () {
            $scope.showVersion = !$scope.showVersion;
        };

        $scope.shouldShowReflections = function () {
            return $scope.wordReflections.reflections.length > 0;
        };
*/



        $scope.selectThumbnail = function(thumbnail){
            console.log(thumbnail);
        };

        $scope.init = function () {
            $scope.carouselItems=[];
            $scope.wordId = $route.current.params.id;

            wordService.getWord($scope.wordId).success(function(response){
                $scope.wordDetails = response;
                if(!_.isEmpty($scope.wordDetails.wordIntroductions )){
                    $scope.carouselItems.push(new AjabShahar.ThumbnailObject($scope.wordDetails,"word"))
                }
                $scope.carouselItems = $scope.carouselItems.concat(
                    wordService
                        .getReflectionsFrom($scope.wordDetails)
                        .map(function(reflection){
                            return new AjabShahar.ThumbnailObject(reflection,"reflection");
                        }
                    )
                )
            });

        }();
    }]);

