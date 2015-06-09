var wordsAdminApp = angular.module('wordsAdminApp', ['textAngular', 'multi-select', 'adminCommon','commonApp']);
wordsAdminApp.factory('contentService', ['$http', contentService]);

wordsAdminApp.config(function ($locationProvider,$provide) {
    $locationProvider.html5Mode({
        enabled: true
    });

    if(!_.isEmpty(rangy)) {
        $provide.decorator('taOptions', ['taRegisterTool', '$delegate', '$log',
            function (taRegisterTool, taOptions, $log) {
                rangy.init();
                var coupletApplier = rangy.createClassApplier("couplet", {normalize: true});
                taRegisterTool('couplet', {
                    iconclass: "fa fa-comment",
                    buttontext: "couplet",
                    action: function (deferred, restoreSelection) {
                        return coupletApplier.toggleSelection();
                    },
                    activeState: function (commonElement) {
                        return coupletApplier.isAppliedToSelection();
                    }
                });
                taOptions.toolbar[1].push('couplet');
                return taOptions;
            }]);
    }
});

wordsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/partials/home.html",
    "EDIT": "/admin/partials/songs/edit.html",
    "DETAILS": "/admin/partials/songs/details.html"
});
