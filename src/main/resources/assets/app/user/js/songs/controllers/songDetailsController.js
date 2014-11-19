var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = this;
    $scope.urlId = $location.search().id;
    $scope.detailsService = $scope;
    $scope.sections = [];

    $scope.init = function(){
        songsContentService.getSongsVersions($scope.urlId).then(function(result){
            var songs = result.data;
            _.reduce(songs,function(sections, song,index) {
                var sectionIndex = index%3;
                if(sectionIndex==0)
                {
                    sections.push({});
                    sections[sections.length-1].index = sectionIndex;
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

    $scope.init();
};

songDetailsApp
.controller('songDetailsController',['$scope','$location','songsContentService',songDetailsController])
.animation('.slide-animation', carouselAnimation);