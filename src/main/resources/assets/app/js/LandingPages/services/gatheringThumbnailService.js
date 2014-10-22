gatheringThumbnailService = function (){
    var getName = function (person) {
      var firstName = person.firstName,
      middleName = person.middleName,
      lastName = person.lastName;

      return firstName + prependSpaceIfNotEmpty(middleName) + prependSpaceIfNotEmpty(lastName);
    };

    var prependSpaceIfNotEmpty = function(string) {
      return (string != "") ? string = ' ' + string : string = string;
    };

    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<gathering-with-details overlay-id="'+id  +'"'+
              ' custom-style="'+customStyle+'"' +
              ' img-src="'+details.thumbnail_url+'"'+
              ' name="'+details.name+'"'+
              ' location="'+details.location+'"' +
              ' date="'+details.date+'">'+
              '</gathering-with-details>';
    }


return {
    getThumbnailWithBubble: getThumbnailWithBubble,
  };
};

htmlGenerator.factory('gatheringThumbnailService',[gatheringThumbnailService]);