var reflectionsAdminApp = angular.module('reflectionsAdminApp', ['textAngular','multi-select', 'adminCommon']);
reflectionsAdminApp.factory('reflectionContentService', ['$http', reflectionContentService]);

reflectionsAdminApp.config(function ($locationProvider,$provide) {
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
