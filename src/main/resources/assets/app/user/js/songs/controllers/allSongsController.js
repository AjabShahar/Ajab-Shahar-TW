var allSongsController = function($scope,contentService,songThumbnailService,introductionPopupService,popupService){
    $scope.popupService = popupService;
    $scope.thumbnailContent=null;
    $scope.paginationthumbnailContent=null;

    $scope.init = function(){
    	contentService.getMainLandingPageThumbnails().then(function(result){

			var getShiftIndex = function(index){
		        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
		    };
    		var startIndex =-1;
    		var details = result.data;

    		$scope.thumbnailContent = _.reduce(details.songs, function(memo, value, index){
                startIndex++;
                return memo+songThumbnailService.getThumbnailWithBubble(details.songs[index],'song_'+details.songs[index].id,'');
        	},'');

    	});
    }

    $scope.addMoreItems = function(){
        console.log("show other");
    }

    $scope.init();
};

allSongsApp.controller('allSongsController',['$scope','contentService','songThumbnailService','introductionPopupService','popupService',allSongsController]);