var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = this;
    $scope.urlId = $location.search().id;
    $scope.detailsService = $scope;
    $scope.sections = [];

    $scope.init = function(){
        songsContentService.getSongsVersions($scope.urlId).then(function(result){
            var songs = result.data;
            _.reduce(songs,function(sections, song,index) {
                if(index%3==0)
                {
                    sections.push({});
                    sections[sections.length-1].songs = [];
                }
                sections[sections.length-1].songs.push(song);
                return sections;
            },$scope.sections);
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.urlId).then(function(result){
            $scope.details = result.data;
//            $scope.details[0].showContentDetails = true;
        });
    }

    $scope.open = function(id){
        _.each($scope.details, function(detail) {
            detail.showContentDetails = (detail.id == id);
        });
    }

    $scope.isVideo = function(id){
        return true;
    }

    $scope.isAudio = function(id){
        return false;
    }

        $scope.direction = 'left';
        $scope.currentIndex = 0;

        $scope.setCurrentSlideIndex = function (index) {
            $scope.direction = (index > $scope.currentIndex) ? 'left' : 'right';
            $scope.currentIndex = index;
        };

        $scope.isCurrentSlideIndex = function (index) {
            return $scope.currentIndex === index;
        };

        $scope.prevSlide = function () {
            $scope.direction = 'left';
            $scope.currentIndex = ($scope.currentIndex < $scope.slides.length - 1) ? ++$scope.currentIndex : 0;
        };

        $scope.nextSlide = function () {
            $scope.direction = 'right';
            $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : $scope.slides.length - 1;
        };

    $scope.init();
};

songDetailsApp
.controller('songDetailsController',['$scope','$location','songsContentService',songDetailsController])
.animation('.slide-animation', carouselAnimation);