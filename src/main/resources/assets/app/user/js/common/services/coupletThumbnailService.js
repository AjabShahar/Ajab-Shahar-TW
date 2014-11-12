coupletThumbnailService = function (nameService){

    var getPopupDetails = function(details,startIndex,id,index){
        return '<pop-up ng-init="detailsService.init('+startIndex+',\''+id+'\')" id="'+id+'" width="100" on-next="detailsService.next('+index+')" on-prev="detailsService.prev('+index+')" show="detailsService.shouldShow(\''+id+'\')"'
        + ' on-close="detailsService.onClose(\''+id+'\')" closeButton="true">'
        + '<couplet-introduction id="'+id+'" poet="'+nameService.getName(details.poet)+'" name="'+details.englishTranslation+'"'
        + ' close-video="detailsService.isClosed(\''+id+'\')"></couplet-introduction>'
        +'</pop-up>';

    };

    var getThumbnailWithBubble = function(details,id,customStyle){
        return '<couplet-with-details id="'+id +'"'+
        ' custom-style="'+customStyle+'"' +
        ' open="detailsService.open(\''+id+'\')"'+
        ' title="'+details.englishTransliteration+'"'+
        ' english-translation-text="'+details.englishTranslationText+'"'+
        ' category-name="'+details.category.name+'"'+
        ' img-src="'+details.thumbnail_url+'"'+
        '</couplet-with-details>';
    };

     return {
     getPopupDetails:getPopupDetails,
     getThumbnailWithBubble: getThumbnailWithBubble,
    };
};

htmlGenerator.factory('coupletThumbnailService',['nameService',coupletThumbnailService]);