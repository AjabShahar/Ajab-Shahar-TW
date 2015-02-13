ajabShaharScrollBar.directive("customScrollBar", function () {
    return {
        link : function(scope,element,attrs){
            $(element).jScrollPane({
                width:'600px',
                height:'500px',
                background:'#FF0000'
            });
        }
    }
});