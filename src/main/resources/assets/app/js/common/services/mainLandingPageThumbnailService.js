var mainLandingPageThumbnailService= function (songThumbnailService,coupletThumbnailService,wordThumbnailService,
filmThumbnailService,gatheringThumbnailService,reflectionThumbnailService) {
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getThumbnailWithBubble = function (details,contentType) {
        var startIndex =-1;
        var songs = _.reduce(details.songs, function(memo, value, index){
                startIndex++;
                return memo+songThumbnailService.getThumbnailWithBubble(details.songs[index],'song_'+details.songs[index].id,"shift"+getShiftIndex(startIndex));
        },'');

        var couplets = _.reduce(details.couplets, function(memo, value, index){
                startIndex++;
                return memo+coupletThumbnailService.getThumbnailWithBubble(details.couplets[index],'oid'+startIndex,"shift"+getShiftIndex(startIndex));
        },'');

        var words = _.reduce(details.words, function(memo, value, index){
                startIndex++;
                return memo+wordThumbnailService.getThumbnailWithBubble(details.words[index],'oid'+startIndex,"shift"+getShiftIndex(startIndex));
        },'');

        var film = _.reduce(details.films, function(memo, value, index){
                        startIndex++;
                return memo+filmThumbnailService.getThumbnailWithBubble(details.films[index],'oid'+startIndex,"shift"+getShiftIndex(startIndex));
        },'');

        var gathering = _.reduce(details.gatherings, function(memo, value, index){
                startIndex++;
                return memo+gatheringThumbnailService.getThumbnailWithBubble(details.gatherings[index],'oid'+startIndex,"shift"+getShiftIndex(startIndex));
        },'');

        var reflection = _.reduce(details.reflections, function(memo, value, index){
                startIndex++;
                return memo+reflectionThumbnailService.getThumbnailWithBubble(details.reflections[index],'oid'+startIndex,"shift"+getShiftIndex(startIndex));
        },'');

        return songs + couplets + words+film+gathering+reflection;
  };

  return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('mainLandingPageThumbnailService',['songThumbnailService','coupletThumbnailService','wordThumbnailService',
'filmThumbnailService','gatheringThumbnailService','reflectionThumbnailService', mainLandingPageThumbnailService]);