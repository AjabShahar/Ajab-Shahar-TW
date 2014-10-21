htmlGenerator.factory('thumbnailService', function () {
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var prependSpaceIfNotEmpty = function(string) {
      return (string != "") ? string = ' ' + string : string = string;
    };

    var getName = function (person) {
      var firstName = person.firstName,
      middleName = person.middleName,
      lastName = person.lastName;

      return firstName + prependSpaceIfNotEmpty(middleName) + prependSpaceIfNotEmpty(lastName);
    };

    var getThumbnailWithBubble = function (details) {
    return _.reduce(details, function(memo, value, index){
          shiftIndex = getShiftIndex(index);
          
          if(value.thumbnailType=='Songs'){
              return memo + '<song-with-details overlay-id="oid'+index +'"'+
              ' open="open(\'oid'+index+'\')"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' url="'+details[index].youtubeVideoId+'"'+
              ' name="'+details[index].englishTranslationTitle+'"'+
              //' singer="' + getName(details[index].singers[0]) + '"' +
              ' category-name="'+details[index].categoryName+'"'+
              ' duration="'+ details[index].duration+'"'+
              //' poet="' + getName(details[index].poets[0]) + '">
              '</song-with-details>';
          }

          if(value.thumbnailType=='Films'){
              return memo + '<film-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' context="'+details[index].context+'"'+ '></film-with-details>';
          }

          if(value.thumbnailType=='Reflections'){
              return memo + '<reflection-with-details overlay-id="oid'+index +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' by="'+details[index].by+'"></reflection-with-details>';
          }

          if(value.thumbnailType=='Words'){
              return memo + '<word-with-details overlay-id="oid'+index + '"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' contextual-meaning="'+details[index].contextualMeaning+'">' +
              '</word-with-details>';
          }

          if(value.thumbnailType=='Gathering'){
              return memo + '<gathering-with-details overlay-id="oid'+index  +'"'+
              ' custom-style="shift'+shiftIndex+'"' +
              ' img-src="'+details[index].thumbnail_url+'"'+
              ' name="'+details[index].name+'"'+
              ' location="'+details[index].location+'"' +
              ' date="'+details[index].date+'">'+
              '</gathering-with-details>';
          }

          if(value.thumbnailType=='Couplets'){
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
});