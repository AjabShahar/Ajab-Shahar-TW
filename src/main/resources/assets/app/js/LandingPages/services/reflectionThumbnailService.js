reflectionThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<reflection-with-details overlay-id="'+id +'"'+
        ' custom-style="'+customStyle+'"' +
        ' img-src="'+details.thumbnail_url+'"'+
        ' name="'+details.name+'"'+
        ' by="'+details.by+'"></reflection-with-details>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('reflectionThumbnailService',[reflectionThumbnailService]);
