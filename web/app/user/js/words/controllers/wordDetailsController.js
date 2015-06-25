angular.module("word").
    controller('wordDetailsController', ['$scope','wordService', '$route',function($scope,wordService, $route){

        var carouselOpen = true;

        $scope.containsMoreThanOneItem = function() {
            return $scope.carouselItems.length > 1;
        };

        $scope.shouldShowCarousel = function(){
            return  carouselOpen && $scope.containsMoreThanOneItem();
        };

        $scope.toggleCarousel = function(){
            carouselOpen = !carouselOpen;
        };

        $scope.reflectionCount = function(){
            var count = 0;
            if(!_.isEmpty($scope.wordDetails)){
                //if(!_.isEmpty($scope.wordDetails.defaultReflection)) count++;
                if(!_.isEmpty($scope.wordDetails.reflections)) {
                    var reflectionCount = _.countBy($scope.wordDetails.reflections,function(reflection){
                        return reflection.published? 'published':'unpublished';
                    });
                    count+= reflectionCount.published;
                }
            }
            return count;
        };

        $scope.selectThumbnail = function(thumbnail){
            if(_.isEmpty(thumbnail)) return;
            $scope.detailsObject = {};
            if(thumbnail.type === 'word'){
                $scope.detailsObject = new AjabShahar.DetailsObject($scope.wordDetails,thumbnail.type)
            }
            else if (thumbnail.type === 'reflection'){
                wordService.getReflection(thumbnail.id).success(function(response){
                    $scope.detailsObject =new AjabShahar.DetailsObject(response,thumbnail.type)
                })
            }
        };

        $scope.init = function () {
            $scope.carouselItems=[];
            $scope.wordId = $route.current.params.id;
            wordService.getWord($scope.wordId).success(function(response){
                $scope.wordDetails = response;
                $scope.associatedWords = [].concat($scope.wordDetails.synonyms).concat($scope.wordDetails.relatedWords);
                $scope.containsWordIntro =  (!_.isEmpty($scope.wordDetails)  && !_.isEmpty($scope.wordDetails.wordIntroduction) && !_.isEmpty($scope.wordDetails.wordIntroduction.wordIntroEnglish));

                if(!_.isEmpty($scope.wordDetails.wordIntroduction ) && !_.isEmpty($scope.wordDetails.wordIntroduction.wordIntroEnglish)){
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

