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

    var getSongThumbnailWithBubble = function(details,id){
          return '<song-with-details overlay-id="'+id +'"'+
          ' open="open(\''+id+'\')"'+
          ' custom-style="shift'+shiftIndex+'"' +
          ' img-src="'+details.thumbnail_url+'"'+
          ' url="'+details.youtubeVideoId+'"'+
          ' name="'+details.englishTranslationTitle+'"'+
          ' category-name="'+details.categoryName+'"'+
          ' duration="'+ details.duration+'"'+
          ' singer="' + getName(details.singers[0]) + '"' +
          ' poet="' + getName(details.poets[0]) + '">' +
          '</song-with-details>';
    }


return {
    getSongThumbnailWithBubble: getSongThumbnailWithBubble,
  };
};

htmlGenerator.factory('songThumbnailService',[songThumbnailService]);
