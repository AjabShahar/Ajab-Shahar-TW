htmlGenerator.factory('introductionPopupService', function () {
  var getPopupDetails = function (details) {
      return _.reduce(details, function(memo, value, index){
          if(value.category=='Songs'){
              return memo+
                  '<pop-up id="oid'+index+'" width="100" show="shouldShow(\'oid'+index+'\')"'+
                  ' on-close="onClose(\'oid'+index+'\')" closeButton="true">'+
                      '<song-introduction singer="'+details[index].singer+'" name="'+details[index].name+'" url="'+details[index].youtubeVideoId+'"></song-introduction>'+
                  '</pop-up>';
          }
          return memo;
      },'');
  };

  return {
    getPopupDetails: getPopupDetails,
  };
});