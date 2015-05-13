angular.module("reflection").factory('reflectionMapper',[function () {
    var getThumbnails = function (reflections, customStyle) {
        return _.reduce(reflections, function (thumbnails, reflection) {
            thumbnails.push({
                "id": reflection.id,
                "contentId": "reflection_" + reflection.id,
                "isReflection": true,
                "title": reflection.title,
                "verb": reflection.verb,
                "speaker": reflection.speaker,
                "customStyle": (customStyle) ? customStyle() : ''
            });
            return thumbnails;
        }, []);
    };

    var getOverviews = function (reflections) {
        return _.reduce(reflections, function (overview, reflection) {
            overview.push({
                "id": reflection.id,
                "contentId": "reflection_" + reflection.id,
                "isReflection": true,
                "title": reflection.title,
                "verb": reflection.verb,
                "speaker": reflection.speaker,
                "videoId": reflection.youtubeVideoId,
                "audioUrl": reflection.soundCloudId,
                "text": reflection.reflectionTranscript,
                "info": reflection.info,
                "people":reflection.people,
                "words": reflection.words,
                "songs": reflection.songs
            });
            return overview;
        }, []);
    };

    var getReflectionsCompleteInfo = function (reflections) {
        return _.reduce(reflections, function (completeInfo, reflection) {
            completeInfo.push({
                "id": reflection.id,
                "title": reflection.title,
                "verb": reflection.verb,
                "speaker": reflection.speaker,
                "videoId": reflection.youtubeVideoId,
                "audioUrl": reflection.soundCloudId,
                "text": reflection.reflectionTranscript
            });
            return completeInfo;
        }, []);

    };


    return {
        getThumbnails: getThumbnails,
        getOverviews: getOverviews,
        getReflectionsCompleteInfo: getReflectionsCompleteInfo
    };
}]);
