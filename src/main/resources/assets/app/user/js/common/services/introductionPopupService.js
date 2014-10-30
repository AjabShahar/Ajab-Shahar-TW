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
      var startIndex =-1;

      return _.reduce(details.songs, function(memo, value, index){
            startIndex++;
            var id = 'song_'+details.songs[index].id;
            return memo+
                '<pop-up ng-init="popupService.init('+startIndex+',\''+id+'\')" id="'+id+'" width="100" on-next="popupService.next('+index+')" on-prev="popupService.prev('+index+')" show="popupService.shouldShow(\''+id+'\')"'
                + ' on-close="popupService.onClose(\''+id+'\')" closeButton="true">'
                  + '<song-introduction id="'+id+'" singer="'+getName(details.songs[index].singers[0])+'" name="'+details.songs[index].englishTranslation+'"'
                   + ' url="'+details.songs[index].youtubeVideoId+'" close-video="popupService.isClosed(\''+id+'\')"></song-introduction>'
                +'</pop-up>';
      },'');
  };

  return {
    getPopupDetails: getPopupDetails,
  };
});