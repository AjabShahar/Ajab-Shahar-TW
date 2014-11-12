htmlGenerator.factory('introductionPopupService', function (songThumbnailService,coupletThumbnailService) {
    var getPopupDetails = function (details,contentType) {
      var startIndex =-1;

      var songPopups = _.reduce(details.songs, function(memo, value, index){
            startIndex++;
            var id = 'song_'+details.songs[index].id;
            return memo+songThumbnailService.getPopupDetails(details.songs[index],startIndex,id,index);
      },'');

//      var coupletPopups = '';
      var coupletPopups = _.reduce(details.couplets, function(memo, value, index){
            startIndex++;
            var id = 'couplet_'+details.couplets[index].id;
            return memo+coupletThumbnailService.getPopupDetails(details.couplets[index],startIndex,id,index);

      },'');

      return songPopups + coupletPopups;
  };

  return {
    getPopupDetails: getPopupDetails,
  };
});