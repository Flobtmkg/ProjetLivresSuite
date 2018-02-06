 function getWindowHeight() {
            var windowHeight=0;
            if (typeof(window.innerHeight)=='number') {
                windowHeight=window.innerHeight;
            } else {
                if (document.documentElement&& document.documentElement.clientHeight) {
                    windowHeight = document.documentElement.clientHeight;
                } else {
                    if (document.body&&document.body.clientHeight) {
                        windowHeight=document.body.clientHeight;
                    }
                }
            }
            return windowHeight;
        }
function getWindowWidth() {
    var windowWidth=0;
    if (typeof(window.innerWidth)=='number') {
        windowWidth=window.innerWidth;
    } else {
        if (document.documentElement&& document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            if (document.body&&document.body.clientWidth) {
                windowWidth=document.body.clientWidth;
            }
        }
    }
    return windowWidth;
}

 function changeImage(){
    //
     var ny=getWindowHeight();
     var nx=getWindowWidth();
     var rapport=nx/ny;
     if(rapport>1.83){
         document.body.style.backgroundImage="url('" + document.getElementById('imageHd').src + "')";
     }else{
         document.body.style.backgroundImage="url('" + document.getElementById('imagenormale').src + "')";
     }
 }

