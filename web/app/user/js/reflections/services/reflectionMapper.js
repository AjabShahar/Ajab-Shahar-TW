var reflectionMapper = function () {
    getThumbnails = function(reflections,customStyle) {
        return _.reduce(reflections,function(thumbnails, reflection,index) {
            thumbnails.push({
                "id":reflection.id,
                "contentId":"reflection_"+reflection.id,
                "isReflection":true,
                "title":reflection.title,
                "verb":reflection.verb,
                "speaker":reflection.speaker,
                "customStyle": (customStyle)? customStyle():'',
            });
            return thumbnails;
        },[]);
    };

    getOverviews = function(reflections,customStyle) {
         return _.reduce(reflections,function(overview, reflection,index) {
             overview.push({
                 "id":reflection.id,
                 "contentId":"reflection_"+reflection.id,
                 "isReflection":true,
                 "title":reflection.title,
                 "verb":reflection.verb,
                 "speaker":reflection.speaker,
                 "videoId":reflection.youtubeVideoId,
                 "audioUrl":reflection.soundCloudId,
                 "text":reflection.transcript,
             });
             return overview;
         },[]);
    };

    getReflectionsCompleteInfo = function(reflections){
         return _.reduce(reflections,function(completeInfo, reflection,index) {
             completeInfo.push({
                 "id":reflection.id,
                 "title":reflection.title,
                 "verb":reflection.verb,
                 "speaker":reflection.speaker,
                 "videoId":reflection.youtubeVideoId,
                 "audioUrl":reflection.soundCloudId,
                 "text":reflection.transcript,
              });
              return completeInfo;
          },[]);

    };

    return {
        getThumbnails: getThumbnails,
        getOverviews:getOverviews,
        getReflectionsCompleteInfo: getReflectionsCompleteInfo,
    };
};

thumbnailModule.factory('reflectionMapper', [reflectionMapper]);
