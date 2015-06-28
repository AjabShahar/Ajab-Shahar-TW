var songsAdminApp = angular.module('songsAdminApp', ['multi-select', 'textAngular', 'commonApp', 'adminCommon']);
songsAdminApp.factory('songContentService', ['$http', songContentService]);

songsAdminApp.config(function ($locationProvider,$provide) {
    $locationProvider.html5Mode({enabled: true});

    //var rangy = rangy || {};
    if(!_.isEmpty(rangy)){
        $provide.decorator('taOptions', ['taRegisterTool', '$delegate', '$log',
            function(taRegisterTool, taOptions, $log){
                rangy.init();
                var coupletApplier = rangy.createClassApplier("initial-couplets", {normalize: true});
                taRegisterTool('couplet', {
                    iconclass: "fa fa-comment",
                    buttontext: "couplet",
                    action: function(deferred, restoreSelection){
                        return coupletApplier.toggleSelection();
                    },
                    activeState: function(commonElement) {
                        return coupletApplier.isAppliedToSelection();
                    }
                });
                taOptions.toolbar[1].push('couplet');

                var refrainApplier = rangy.createClassApplier("refrain", {normalize: true});
                taRegisterTool('refrain', {
                    iconclass: "fa fa-ellipsis-v",
                    buttontext: "refrain",
                    action: function(deferred, restoreSelection){
                        return refrainApplier.toggleSelection();
                    },
                    activeState: function(commonElement) {
                        return refrainApplier.isAppliedToSelection();
                    }
                });

                taOptions.setup.textEditorSetup=function($element){
                    $element.addClass('original-html-size');
                };

                taOptions.toolbar[1].push('refrain');
                return taOptions;
            }]);
    }

});

songsAdminApp.constant("PAGES", {
    "ADMIN_HOME": "/admin/partials/home.html",
    "EDIT": "/admin/partials/songs/edit.html",
    "DETAILS": "/admin/partials/songs/details.html"
});
