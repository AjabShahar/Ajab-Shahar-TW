htmlGenerator.factory('thumbnailService', function () {
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getThumbnailWithBubble = function (details) {
    return _.reduce(details, function(memo, value, index){
          shiftIndex = getShiftIndex(index);
          if(value.category=='Songs'){
              return memo + '<song-with-details overlay-id="oid'+index +'"'+
              ' open="open(\'oid'+index+'\')"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' url="'+details[index].youtubeVideoId+'"'+
              ' name="'+details[index].name+'"'+
              ' singer="'+details[index].singer+'"' +
              ' category-name="'+details[index].categoryName+'"'+
              ' poet="'+details[index].poet+'"></song-with-details>';
          }

          if(value.category=='Films'){
              return memo + '<film-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' name="'+details[index].name+'"'+
              ' context="'+details[index].context+'"'+ '></film-with-details>';
          }

          if(value.category=='Reflections'){
              return memo + '<reflection-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' name="'+details[index].name+'"'+
              ' by="'+details[index].by+'"></reflection-with-details>';
          }

          if(value.category=='Words'){
              return memo + '<word-with-details overlay-id="oid'+index + '"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' name="'+details[index].name+'"'+
              ' contextual-meaning="'+details[index].contextualMeaning+'">' +
              '</word-with-details>';
          }

          if(value.category=='Gathering'){
              return memo + '<gathering-with-details overlay-id="oid'+index  +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' name="'+details[index].name+'"'+
              ' location="'+details[index].location+'"' +
              ' date="'+details[index].date+'">'+
              '</gathering-with-details>';
          }

          if(value.category=='Couplets'){
              return memo + '<couplet-with-details overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].imageUrl+'"'+
              ' name="'+details[index].name+'"'+
              '</couplet-with-details>';
          }

          return memo + '<unknown-format overlay-id="oid'+index +'" custom-style="shift'+shiftIndex+'"' +
                  ' img-src="'+details[index].imageUrl+'"'+
                  ' name="'+details[index].name+'"'+
                  ' description="'+details[index].description+'">'+
                  '</unknown-format>'},'');
  };

  return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
});