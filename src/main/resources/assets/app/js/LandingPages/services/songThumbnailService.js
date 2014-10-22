songThumbnailService = function (){
    var getName = function (person) {
      var firstName = person.firstName,
      middleName = person.middleName,
      lastName = person.lastName;

      return firstName + prependSpaceIfNotEmpty(middleName) + prependSpaceIfNotEmpty(lastName);
    };

    var prependSpaceIfNotEmpty = function(string) {
      return (string != "") ? string = ' ' + string : string = string;
    };

    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getThumbnailWithBubble = function(details,id,customStyle){
          return '<song-with-details overlay-id="'+id +'"'+
          ' open="open(\''+id+'\')"'+
          ' custom-style="'+customStyle+'"' +
          ' img-src="'+details.thumbnail_url+'"'+
          ' url="'+details.youtubeVideoId+'"'+
          ' name="'+details.englishTranslationTitle+'"'+
          ' category-name="'+details.categoryName+'"'+
          ' duration="'+ details.duration+'"'+
          ' singer="' + getName(details.singers[0]) + '"' +
          ' poet="' + getName(details.poets[0]) + '">' +
          '</song-with-details>';
    }

    var getThumbnailsWithBubble = function (details,contentType) {
        return _.reduce(details, function(memo, value, index){
                return memo+getThumbnailWithBubble(details[index],'oid'+index,"shift"+getShiftIndex(index));
        },'');
    }

return {
    getThumbnailWithBubble: getThumbnailWithBubble,
    getThumbnailsWithBubble: getThumbnailsWithBubble,
  };
};

htmlGenerator.factory('songThumbnailService',[songThumbnailService]);
