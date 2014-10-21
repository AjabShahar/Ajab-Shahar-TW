var thumbnailService= function (songThumbnailService) {
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getThumbnailWithBubble = function (details,contentType) {
    return _.reduce(details, function(memo, value, index){
          shiftIndex = getShiftIndex(index);
          
          if(contentType||value.contentType=='Songs'){
            return memo+songThumbnailService.getSongThumbnailWithBubble(details[index],'oid'+index);
          }

          if(value.contentType=='Films'){
              return memo + '<film-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' context="'+details[index].context+'"'+ '></film-with-details>';
          }

          if(value.contentType=='Reflections'){
              return memo + '<reflection-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' by="'+details[index].by+'"></reflection-with-details>';
          }

          if(value.contentType=='Words'){
              return memo + '<word-with-details overlay-id="oid'+index + '"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' contextual-meaning="'+details[index].contextualMeaning+'">' +
              '</word-with-details>';
          }

          if(value.contentType=='Gathering'){
              return memo + '<gathering-with-details overlay-id="oid'+index  +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' location="'+details[index].location+'"' +
              ' date="'+details[index].date+'">'+
              '</gathering-with-details>';
          }

          if(value.contentType=='Couplets'){
              return memo + '<couplet-with-details overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              '</couplet-with-details>';
          }

          return memo + '<unknown-format overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'"' +
                  ' img-src="'+details[index].thumbnail_url+'"'+
                  ' name="'+details[index].name+'"'+
                  ' description="'+details[index].description+'">'+
                  '</unknown-format>'},'');
  };

  return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('thumbnailService',['songThumbnailService', thumbnailService]);