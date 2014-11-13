var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = this;
    $scope.urlId = $location.search().id;
    $scope.detailsService = $scope;

    $scope.init = function(){
        songsContentService.getSongsVersions($scope.urlId).then(function(result){
            $scope.carouselSections = result.data;
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

        $scope.slides = [
            {image: 'http://www.pachd.com/free-images/abstract-images/abstract-04.jpg', description: 'Image 00'},
            {image: 'http://www.pachd.com/free-images/abstract-images/abstract-04.jpg', description: 'Image 01'},
            {image: 'http://www.pachd.com/free-images/abstract-images/abstract-04.jpg', description: 'Image 02'},
            {image: 'http://www.pachd.com/free-images/abstract-images/abstract-04.jpg', description: 'Image 03'},
            {image: 'http://www.pachd.com/free-images/abstract-images/abstract-04.jpg', description: 'Image 04'}
        ];

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
.animation('.slide-animation', function () {
    return {
        beforeAddClass: function (element, className, done) {
            var scope = element.scope();

            if (className == 'ng-hide') {
                var finishPoint = element.parent().width();
                if(scope.direction !== 'right') {
                    finishPoint = -finishPoint;
                }
                TweenMax.to(element, 0.5, {left: finishPoint, onComplete: done });
            }
            else {
                done();
            }
        },
        removeClass: function (element, className, done) {
            var scope = element.scope();

            if (className == 'ng-hide') {
                element.removeClass('ng-hide');

                var startPoint = element.parent().width();
                if(scope.direction === 'right') {
                    startPoint = -startPoint;
                }

                TweenMax.fromTo(element, 0.5, { left: startPoint }, {left: 0, onComplete: done });
            }
            else {
                done();
            }
        }
    };
});