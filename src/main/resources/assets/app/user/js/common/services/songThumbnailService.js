var songThumbnailService = function (nameService){
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getThumbnailWithBubble = function(details,id,customStyle){
          return '<song-with-details id="'+id +'"'+
          ' open="popupService.open(\''+id+'\')"'+
          ' custom-style="'+customStyle+'"' +
          ' img-src="'+details.thumbnail_url+'"'+
          ' url="'+details.youtubeVideoId+'"'+
          ' name="'+details.englishTranslation+'"'+
          ' category-name="'+details.songCategory.name+'"'+
          ' duration="'+ details.duration+'"'+
          ' singer="' + nameService.getName(details.singers[0]) + '"' +
          ' class = "songs"' +
          ' poet="' + nameService.getName(details.poets[0]) + '">' +
          '</song-with-details>';
    }

    var getThumbnailsWithBubble = function (details,contentType) {
        return _.reduce(details, function(memo, value, index){
                return memo+getThumbnailWithBubble(details[index],'song_'+details[index].id,"shift"+getShiftIndex(index));
        },'');
    }

return {
    getThumbnailWithBubble: getThumbnailWithBubble,
    getThumbnailsWithBubble: getThumbnailsWithBubble,
  };
};

htmlGenerator.factory('songThumbnailService',['nameService',songThumbnailService]);