reflectionThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<reflection overlay-id="'+id +'"'+
        ' custom-style="'+customStyle+'"' +
        ' img-src="'+details.thumbnail_url+'"'+
        ' name="'+details.name+'"'+
        ' by="'+details.by+'"></reflection>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('reflectionThumbnailService',[reflectionThumbnailService]);
