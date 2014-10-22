htmlGenerator.factory('introductionPopupService', function () {
    var getName = function (person) {
      var firstName = person.firstName,
      middleName = person.middleName,
      lastName = person.lastName;

      return firstName + prependSpaceIfNotEmpty(middleName) + prependSpaceIfNotEmpty(lastName);
    };

    var prependSpaceIfNotEmpty = function(string) {
      return (string != "") ? string = ' ' + string : string = string;
    };

  var getPopupDetails = function (details,contentType) {
      return _.reduce(details.songs, function(memo, value, index){
            return memo+
                '<pop-up id="oid'+index+'" width="100" show="shouldShow(\'oid'+index+'\')"'+
                ' on-close="onClose(\'oid'+index+'\')" closeButton="true">'+
                  '<song-introduction singer="'+getName(details.songs[index].singers[0])+'" name="'+details.songs[index].englishTranslationTitle
                    +'" url="'+details.songs[index].youtubeVideoId+'" close-video="isClosed(\'oid'+index+'\')"></song-introduction>'+
                '</pop-up>';
      },'');
  };

  return {
    getPopupDetails: getPopupDetails,
  };
});