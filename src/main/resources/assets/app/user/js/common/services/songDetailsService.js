var songDetailsService= function (songThumbnailService) {
    var getThumbnailWithBubble = function (details) {
        var songs ="<carousel>"+ _.reduce(details, function(memo, value, index){
                memo = memo + ((index%3==0) ? '<section><ul class="ajab-ribbon">' : '');
                memo = memo + '<li>'+(songThumbnailService.getThumbnailWithBubble(details[index],'song_'+details[index].id,""))+'</li>';
                memo = memo + ((index%3==2) || (index==details.length) ? '</ul></section>' : '');
                return memo;
        },'');

        return songs+"</carousel>";
  };

  return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('songDetailsService',['songThumbnailService', songDetailsService]);