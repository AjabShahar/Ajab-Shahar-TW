var songDetailsService= function (songThumbnailService) {
    var getThumbnailWithBubble = function (details,contentType) {
        var songs = _.reduce(details.versions, function(memo, value, index){
                memo = (index%3==0) ? '<section><ul class="ajab-ribbon">' : '';
                memo = memo + songThumbnailService.getThumbnailWithBubble(details.songs[index],'song_'+details.songs[index].id,"");
                memo = memo + (index%3==2) || (index==details.versions.length) ? '</ul></section>' : '';
                return memo;
        },'');

        return songs;
  };

  return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('songDetailsService',['songThumbnailService', songDetailsService]);