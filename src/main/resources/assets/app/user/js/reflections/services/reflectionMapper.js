var reflectionMapper = function () {
    getThumbnails = function(reflections,customStyle) {
        return _.reduce(reflections,function(thumbnails, reflection,index) {
            thumbnails.push({
                "id":reflection.id,
                "title":reflection.title,
                "speaker":reflection.speaker,
                "customStyle": (customStyle)? customStyle():'',
            });
            return thumbnails;
        },[]);
    };

    return {
        getThumbnails: getThumbnails,
    };
};

thumbnailModule.factory('reflectionMapper', [reflectionMapper]);