filmThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){

    return '<film overlay-id="'+id +'"'+
                  ' custom-style="'+customStyle+'"' +
                  ' img-src="'+details.thumbnail_url+'"'+
                  ' name="'+details.name+'"'+
                  ' context="'+details.context+'"'+ '></film>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('filmThumbnailService',[filmThumbnailService]);