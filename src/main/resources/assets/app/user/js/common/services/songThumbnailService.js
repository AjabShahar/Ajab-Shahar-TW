var songThumbnailService = function (nameService){
    var getShiftIndex = function(index){
        return ((4+index) % 6) == 0 ? 6 : ((4+index) % 6);
    };

    var getPopupDetails = function(details,startIndex,id,index){
        return '<pop-up ng-init="detailsService.init('+startIndex+',\''+id+'\')" id="'+id+'" width="100" on-next="detailsService.next('+index+')" on-prev="detailsService.prev('+index+')" show="detailsService.shouldShow(\''+id+'\')"'
        + ' on-close="detailsService.onClose(\''+id+'\')" closeButton="true">'
          + '<song-introduction id="'+id+'" singer="'+nameService.getName(details.singers[0])+'" name="'+details.englishTranslation+'"'
          + ' audio-Url="' + details.soundCloudTrackID + '"'
          + ' video-Url="'+details.youtubeVideoId+'" close-video="detailsService.isClosed(\''+id+'\')"></song-introduction>'
        +'</pop-up>';
    }

//    var getSongDetailsForBinding = function(details,id,customStyle){
//        return {"id":id, "customStyle":customStyle,"imgSrc":details.thumbnail_url,"url":details.youtubeVideoId,"name":details.englishTranslation,
//            "categoryName":details.songCategory.name, "duration":details.duration, "singer":nameService.getName(details.singers[0]) ,
//            "poet":nameService.getName(details.poets[0])
//        };
//    };
//
//    var getSongsDetailsForBinding = function(details,id,customStyle){
//        return _.reduce(details, function(memo, value, index){
//                memo.push(getSongDetailsForBinding(details[index],'song_'+details[index].id,"shift"+getShiftIndex(index)));
//                return memo;
//        },[]);
//    };
//
    var getThumbnailWithBubble = function(details,id,customStyle){
          return '<song-with-details id="'+id +'"'+
          ' open="detailsService.open(\''+id+'\')"'+
          ' custom-style="'+customStyle+'"' +
          ' img-src="'+details.thumbnail_url+'"'+
          ' url="'+details.youtubeVideoId+'"'+
          ' name="'+details.englishTranslation+'"'+
          ' category-name="'+details.songCategory.name+'"'+
          ' duration="'+ details.duration+'"'+
          ' singer="' + nameService.getName(details.singers[0]) + '"' +
          ' class = "songs"' +
          ' poet="' + nameService.getName(details.poets[0]) + '">' +
          '</song-with-details>';
    };

    var getThumbnailsWithBubble = function (details,contentType) {
        return _.reduce(details, function(memo, value, index){
                return memo+getThumbnailWithBubble(details[index],'song_'+details[index].id,"shift"+getShiftIndex(index));
        },'');
    };

return {
//    getSongsDetailsForBinding:getSongsDetailsForBinding,
//    getSongDetailsForBinding:getSongDetailsForBinding,
    getPopupDetails:getPopupDetails,
    getThumbnailWithBubble: getThumbnailWithBubble,
    getThumbnailsWithBubble: getThumbnailsWithBubble,
  };
};

htmlGenerator.factory('songThumbnailService',['nameService',songThumbnailService]);