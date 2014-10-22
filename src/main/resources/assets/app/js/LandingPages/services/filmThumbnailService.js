filmThumbnailService = function (){
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
