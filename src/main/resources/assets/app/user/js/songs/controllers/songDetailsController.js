var songDetailsController = function($scope,contentService,songDetailsService){
    $scope.detailsService = this;
    visibilityOfAllVersions = {};

    $scope.open = function(id){
        _.each(visibilityOfAllVersions, function(detail) {
            visibilityOfAllVersions[key] = false;
        });
        visibilityOfAllVersions[id] = true;
    }

    $scope.init = function(){
        contentService.getSongDetails().then(function(result){
            $scope.sections = [
            {
            items:[
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
            ],
            items:[
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
                {"open":"showDetails('song_1')",
                "imgSrc":"http://phpalbum.net/demo4/main.php?cmd=imageorig&amp;var1=IMGP7051a.JPG",
                "videoId":"tNh2kjmSzPw",
                "name":"For a few days,O Heart",
                "categoryName":"undefined",
                "duration":"5:45",
                "singer":"Parvathy Baul",
                "poet":"Roshik"},
            ],
            }];
            $scope.carouselSections = songDetailsService.getThumbnailWithBubble(result.data);
            $scope.detailContent = introductionPopupService.getPopupDetails(result.data);
        });
    }
    //carouselSections
};

songDetailsApp.controller('songDetailsController',['$scope','contentService','songDetailsService',songDetailsController]);