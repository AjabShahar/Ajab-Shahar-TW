coupletThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<couplet-with-details overlay-id="'+id +'" custom-style="'+customStyle+'"' +
        ' img-src="'+details.thumbnail_url+'"'+
        ' name="'+details.name+'"'+
        '</couplet-with-details>';
    }

return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('coupletThumbnailService',[coupletThumbnailService]);