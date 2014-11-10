htmlGenerator.factory('introductionPopupService', function (songThumbnailService) {
    var getPopupDetails = function (details,contentType) {
      var startIndex =-1;

      var songPopups = _.reduce(details.songs, function(memo, value, index){
            startIndex++;
            var id = 'song_'+details.songs[index].id;
            return memo+songThumbnailService.getPopupDetails(details.songs[index],startIndex,id,index);
      },'');

      var coupletPopups = '';
//      var coupletPopups = _.reduce(details.couplets, function(memo, value, index){
//            startIndex++;
//            var id = 'couplet_'+details.couplets[index].id;
//            return memo+
//                '<pop-up ng-init="detailsService.init('+startIndex+',\''+id+'\')" id="'+id+'" width="100" on-next="detailsService.next('+index+')" on-prev="detailsService.prev('+index+')" show="detailsService.shouldShow(\''+id+'\')"'
//                + ' on-close="detailsService.onClose(\''+id+'\')" closeButton="true">'
//                  + '<couplet-introduction id="'+id+'" poet="'+nameService.getName(details.couplets[index].poets[0])+'" name="'+details.couplets[index].englishTranslation+'"'
//                  + ' close-video="detailsService.isClosed(\''+id+'\')"></couplet-introduction>'
//                +'</pop-up>';
//      },'');

      return songPopups + coupletPopups;
  };

  return {
    getPopupDetails: getPopupDetails,
  };
});