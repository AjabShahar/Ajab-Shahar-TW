var cmsService = function ($http) {
  var getScreenOptions = function () {
    return 	                 {
                                    "options" :[
                                                {"format":"audio","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1","imageUrl":""},
                                                {"format":"video","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"}
                                    ]
                                };
  };

  return {
    getScreenOptions: getScreenOptions,
  };
};

introductionApp.factory('cmsService', ['$http', cmsService]);