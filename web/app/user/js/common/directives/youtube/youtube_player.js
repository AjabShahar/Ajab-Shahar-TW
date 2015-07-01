mediaPlayer.constant('YT_event', {
    STOP: 0,
    PLAY: 1,
    PAUSE: 2
});

mediaPlayer.directive('youtube', function ($window, YT_event, $sce) {
    return {
        restrict: "E",

        scope: {
            height: "@",
            width: "@",
            videoid: "=",
            controls: "@",
            autoplay: "@",
            loop: "@",
            playlist: "@"
        },

        template: '<iframe id="ytplayer" type="text/html" width="{{width}}" height="{{height}}" ng-src="{{videoUrl}}" frameborder="0" allowfullscreen/>',

        link: function (scope) {
            scope.$watch("videoid",function(){
                scope.videoUrl = $sce.trustAsResourceUrl("//www.youtube.com/embed/" + scope.videoid + "?controls=" + scope.controls + "&autoplay=" + scope.autoplay + "&loop=" + scope.loop + "&playlist=" + scope.playlist + "&rel=0&showinfo=0");
            });
        }
    };
});
