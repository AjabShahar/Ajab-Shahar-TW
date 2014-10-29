wordThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<word-with-details overlay-id="'+id + '"'+
        ' custom-style="'+customStyle+'"' +
        ' img-src="'+details.thumbnail_url+'"'+
        ' name="'+details.name+'"'+
        ' contextual-meaning="'+details.contextualMeaning+'">' +
        '</word-with-details>';
    }

return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('wordThumbnailService',[wordThumbnailService]);
