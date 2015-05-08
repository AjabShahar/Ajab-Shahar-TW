mediaPlayer.directive('soundCloud', function ($http, $compile) {
    return {
        restrict: "E",

        scope: {
            height: "@",
            width: "@",
            audioUrl: '@',
            shouldStopVideo: '&'
        },

        link: function (scope, element) {
            scope.$watch("audioUrl",function(newValue){
                scope.maxHeight = scope.maxHeight || "";
                scope.maxWidth = scope.maxWidth || "";
                var soundcloudOembedUrl = "https://soundcloud.com/oembed.json?show_comments=false&";
                var embedOptions = "url=" + scope.audioUrl + "&maxheight=" + scope.maxHeight + "&maxwidth=" + scope.maxWidth;
                $http.get(soundcloudOembedUrl + embedOptions)
                    .success(function (response) {
                        element.html(response.html).show();
                        $compile(element.contents())(scope);
                    })
                    .error(function(response){
                        element.html("<div></div>").show();
                        $compile(element.contents())(scope);
                    });
            });

        }
    };
});
