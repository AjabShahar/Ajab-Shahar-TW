angular.module("word").
    controller('wordDetailsController', ['$scope','wordService', '$route',function($scope,wordService, $route){

        var carouselOpen = true;

        $scope.containsReflections = function() {
            if(!_.isEmpty($scope.wordDetails)){
                return !_.isEmpty($scope.wordDetails.defaultReflection) && !_.isEmpty($scope.wordDetails.reflections);
            }
            return false;
        };
        $scope.shouldShowCarousel = function(){
            return  carouselOpen && $scope.containsReflections()
        };

        $scope.toggleCarousel = function(){
            carouselOpen = !carouselOpen;
        };

        $scope.reflectionCount = function(){
            var count = 0;
            if(!_.isEmpty($scope.wordDetails)){
                if(!_.isEmpty($scope.wordDetails.defaultReflection)) count++;
                if(!_.isEmpty($scope.wordDetails.reflections)) count+= $scope.wordDetails.reflections.length;
            }
            return count;
        };

        $scope.selectThumbnail = function(thumbnail){
            if(_.isEmpty(thumbnail)) return;
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

