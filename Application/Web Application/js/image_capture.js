//function save(){}
function capture(){
			var v = document.querySelector('video'),
      n = document.querySelector('source').src.replace(/.*\/|\..*$/g,''),
      c = document.querySelector('canvas'),
      ctx = c.getContext('2d');
			var ratio = v.videoWidth/v.videoHeight,
      w = v.videoWidth,
      h = v.videoHeight,
      time = 0,
      img = null,
      li = null;
    	c.width = w;
    	c.height = h;
			ctx.fillStyle = 'rgb(0, 0, 0)';
    	ctx.fillRect(0, 0, w, h);
    	ctx.drawImage(v, 0, 0, w, h);
}

(function(){
	var v = document.querySelector('video'),
    c = document.querySelector('canvas'),
    ctx = c.getContext('2d');
		v.addEventListener('loadedmetadata',function(ev){
    var ratio = v.videoWidth/v.videoHeight;
	},false);
  }
)();
