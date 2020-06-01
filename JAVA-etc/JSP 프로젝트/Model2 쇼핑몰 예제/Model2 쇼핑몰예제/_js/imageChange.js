function find(n, d) { 
	var p,i,x;  
	if (!d) {
		d = document; 
	}
	if((p=n.indexOf("?"))>0 && parent.frames.length) {
		d = parent.frames[n.substring(p+1)].document; 
		n = n.substring(0,p);
	}
	if (!(x=d[n])&&d.all) {
		x = d.all[n]; 
	}
	for (i=0;!x&&i<d.forms.length;i++) {
		x=d.forms[i][n];
	}
	for (i=0; !x&&d.layers&&i<d.layers.length; i++) {
		x=find(n,d.layers[i].document); 
	}
	return x;
}

function show() { 	 
	var i,p,v,obj,args=show.arguments;
	for (i=0; i<(args.length-2); i+=3) {
		if ((obj=find(args[i]))!=null) { 
			v=args[i+2];
			if (obj.style) { 
				obj=obj.style; 
				v=(v=='show')?'visible':(v='hide')?'hidden':v; 
			}
			obj.visibility=v; 
		}
	}
}

function rollIn(img) {
	newImg = img + "_on";
	if (document.images)
		document.images[img].src = eval(newImg).src;
}
function rollOut(img) {
	newImg = img + "_off";
	if (document.images)
		document.images[img].src = eval(newImg).src;
}
