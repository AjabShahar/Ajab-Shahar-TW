gatheringThumbnailService = function (){
    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<gathering overlay-id="'+id  +'"'+
              ' custom-style="'+customStyle+'"' +
              ' img-src="'+details.thumbnail_url+'"'+
              ' name="'+details.name+'"'+
              ' location="'+details.location+'"' +
              ' date="'+details.date+'">'+
              '</gathering>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('gatheringThumbnailService',[gatheringThumbnailService]);