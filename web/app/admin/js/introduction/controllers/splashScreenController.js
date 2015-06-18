var splashScreenController = function ($scope, $window, $http, contentService) {
    $scope.formInfo = {};
    $scope.option = {"url": "", "imageUrl": ""};
    $scope.optionsFormatList = [{'id': 'audio', 'name': 'Audio'}, {'id': 'video', 'name': 'Video'}];

    $scope.saveData = function () {
        $http.post('/api/SplashScreenOptions', $scope.formInfo).success(function () {
            $window.location.href = '/admin/partials/home.html';
        });
    };

    $scope.getRandomIntWithRange = function (min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
    };

    $scope.shouldShowImageUrl = function () {
        return ($scope.formInfo.FORMAT == 'audio');
    };
};

splashScreenOptionsApp.controller('splashScreenController', ['$scope', '$window', '$http', 'contentService', splashScreenController]);
