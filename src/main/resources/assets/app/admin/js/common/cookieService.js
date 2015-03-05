commonApp.service("cookieService",function(){
    
        return {
            cookies:function(){
                return $.cookie;
            },
            
            getCookie:function(name){
                return $.cookie['name'];
            },
            setCookie :function(name,value){
                $.cookie['name'] = value;
            },
            
            hasExpired:function(name){
                return $.cookie['name'].expires === 0;
            }
        }
    });