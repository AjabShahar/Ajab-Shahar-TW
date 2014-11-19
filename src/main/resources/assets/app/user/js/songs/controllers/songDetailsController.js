var songDetailsController = function($scope,$location,songsContentService){
    $scope.detailsService = this;
    $scope.urlId = $location.search().id;
    $scope.detailsService = $scope;
    $scope.sections = [];
    $scope.showContentDetails = {};
    $scope.prevId = null;

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

            $scope.open($scope.sections[0].songs[0].id);
        });

        $scope.detailContents = songsContentService.getSongRenditions($scope.urlId).then(function(result){
            $scope.details = result.data;
        });
    }

    $scope.$on('sectionChanged', function(event, args) {
        $scope.open($scope.sections[index].songs[0].id);
    });

    $scope.getSongId = function(id){
        return id.toString().match(/[0-9]+/)[0];
    }

    $scope.open = function(id){
        var songId = $scope.getSongId(id);

        if($scope.prevId!=null)
            $scope.showContentDetails[$scope.prevId] = false;

        $scope.prevId = id;
        $scope.showContentDetails[songId] = true;
    }

    $scope.isOpen = function(id){
        var songId = $scope.getSongId(id);
        return $scope.showContentDetails[songId];
    }

    $scope.isClosed = function(id){
        var songId = $scope.getSongId(id);
        return $scope.showContentDetails[songId];
    }

    $scope.init();
};

songDetailsApp
.controller('songDetailsController',['$scope','$location','songsContentService',songDetailsController])
.animation('.slide-animation', carouselAnimation);