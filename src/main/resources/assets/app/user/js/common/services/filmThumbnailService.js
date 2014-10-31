filmThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){

    return '<film-with-details overlay-id="'+id +'"'+
                  ' custom-style="'+customStyle+'"' +
                  ' img-src="'+details.thumbnail_url+'"'+
                  ' name="'+details.name+'"'+
                  ' context="'+details.context+'"'+ '></film-with-details>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('filmThumbnailService',[filmThumbnailService]);