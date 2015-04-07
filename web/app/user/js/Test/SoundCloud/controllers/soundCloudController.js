var soundCloudController = function ($scope) {
    $scope.init = function () {
        SC.initialize({
            client_id: "694f15bbffd7ae8e6e399f49dd228725"
        });

        console.log("SoundCloud initialized");
    };

    $scope.loadTrack = function (trackID) {

        SC.get("/tracks/" + trackID, {limit: 1}, function (track) {
            console.log("Track URL: " + track.uri);
            SC.oEmbed(track.uri, document.getElementById("track"));
        });


    };

    $scope.pauseTrack = function () {
        var iframeElement = document.querySelector('iframe');
        var widget1 = SC.Widget(iframeElement);
        console.log("working" + widget1);
        widget1.pause();
    };

    $scope.playTracks = function () {
        var iframeElement = document.querySelector('iframe');
        var widget1 = SC.Widget(iframeElement);
        console.log("working" + widget1);
        widget1.play();
    };

    $scope.init();
};

soundCloudApp.controller('soundCloudController', ['$scope', soundCloudController]);
