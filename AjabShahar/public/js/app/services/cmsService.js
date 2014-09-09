var cmsService = function ($http) {
  var getScreenOptions = function () {
    return {
        "options" :[
                    {"format":"audio","url":"O-WVDBpBdRY","imageUrl":"http://localhost/contentAsset/raw-data/ddfcb080-b11c-46c9-bbf2-c39e81a7cd7a/file?byInode=true"},
                    {"format":"video","url":"O-WVDBpBdRY"}
//                    {"format":"audio","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1","imageUrl":"http://localhost/contentAsset/raw-data/ddfcb080-b11c-46c9-bbf2-c39e81a7cd7a/file?byInode=true"},
//                    {"format":"video","url":"https://www.youtube.com/embed/O-WVDBpBdRY?enablejsapi=1"}
        ]
    };
  };

  return {
    getScreenOptions: getScreenOptions,
  };
};

ajabShaharApp.factory('cmsService', ['$http', cmsService]);