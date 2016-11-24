function Player()
{
	this.fileType = {"WMA":"WMP","MP3":"WMP","WMV":"WMP","ASF":"WMP","MPG":"WMP","MPEG":"WMP","AVI":"WMP","ASX":"WMP","RM":"Real","RMVB":"Real","RAM":"Real","RA":"Real","SWF":"Flash","FLV":"Flv"};
	this.filePath;
	this.width = 480;
	this.height = 360;
	this.autoStart = true;
};

Player.prototype.WMP = function()
{
	document.writeln("<object id='mediaplayer' width='"+ this.width +"' height='"+ this.height +"' " +" classid='CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6' align='center' border='0' type='application/x-oleobject' standby='Loading Windows Media Player components...'>");
	document.writeln("<param name='url' value='"+this.filePath+"'>");
	document.writeln("<param name='AutoStart' value='"+ (this.autoStart? "1":"0") +"'><param name='Balance' value='0'><param name='enabled' value='-1'><param name='EnableContextMenu' value='0'><param name='PlayCount' value='1'><param name='rate' value='1'><param name='currentPosition' value='0'><param name='currentMarker' value='0'><param name='defaultFrame' value=''><param name='invokeURLs' value='-1'><param name='baseURL' value=''><param name='stretchToFit' value='0'><param name='volume' value='100'><param name='mute' value='0'><param name='uiMode' value='full'><param name='windowlessVideo' value='0'><param name='fullScreen' value='0'><param name='enableErrorDialogs' value='0'><param name='SAMIStyle' value=''><param name='SAMILang' value=''><param name='SAMIFilename' value=''><param name='captioningID' value=''>");
	document.writeln("</object>");
	document.close();
};

Player.prototype.Real = function()
{
	//视频播放
	/*	
	document.writeln("<object classid='clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA' width='"+ this.width +"' height='"+ this.height +"'  id='amourReal'>");
	document.writeln("  <param name='src' value='"+this.filePath+"'>");
	document.writeln("  <param name='autostart' value='"+ this.autoStart +"'>");
	document.writeln("  <param name='controls' value='imagewindow'>");
	document.writeln("  <param name='console' value='clip1'>");
	document.writeln("  <embed src='"+this.filePath+"' width='"+ this.width +"' height='"+ this.height +"' type='audio/x-pn-realaudio-plugin' autostart='true' controls='imagewindow,StatusBar,controlpanel' console='video'>");
	document.writeln("  </embed>");
	document.writeln("</object>");
	document.writeln("<br>");
	*/

	//无视频播放
	document.writeln("<object classid='clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA' width='"+ this.width +"' height='"+ this.height +"' id='amourReal'>");
	document.writeln("  <param name='src' value='"+this.filePath+"'>");
	document.writeln("  <param name='autostart' value='"+ this.autoStart +"'>");
	document.writeln("  <param name='controls' value='all'>");
	document.writeln("  <param name='console' value='clip1'>");
	document.writeln("  <embed src='"+this.filePath+"' width='"+ this.width +"' height='"+ this.height +"' type='audio/x-pn-realaudio-plugin' autostart='true' controls='all' console='video'>");
	document.writeln("  </embed>");
	document.writeln("</object>");
	document.close();
	//controls='imagewindow,StatusBar,controlpanel 或者 all'
};

Player.prototype.Flash = function()
{
	document.write("<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' width='"+ this.width +"' height='"+ this.height +"'>");
	document.write("<param name='movie' value='"+this.filePath+"'>");
	document.write("<param name='quality' value='high'>");
	document.write("<embed src='"+this.filePath+"' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='"+ this.width +"' height='"+ this.height +"'></embed>");
	document.write("</object>");
	document.close();
};

Player.prototype.Flv = function()
{
	document.writeln("<emBED pluginspage=http://www.macromedia.com/go/getflashplayer src=/r/cms/www/red/images/vcastr.swf?vcastr_file="+ this.filePath.toString() +"&IsAutoPlay=1 width="+ this.width +" height="+ this.height +" type=application/x-shockwave-flash showMovieInfo='0' allowFullScreen='true' wmode='transparent' quality='high' allowNetworking='internal' allowScriptAccess='never'></EMBED");
	document.close();
};

Player.prototype.play = function(filePath,width,height,autoStart)
{
	if (arguments.length>0)
	{
		this.filePath = filePath;
		if(typeof(width)!="undefined") this.width = width;
		if(typeof(height)!="undefined") this.height = height;
		if(typeof(autoStart)!="undefined") this.autoStart = autoStart;

		var pos = this.filePath.lastIndexOf(".");
		if(pos>-1)
		{
			var sufFix = this.filePath.substring(pos+1,this.filePath.length).toUpperCase();
			if(sufFix.indexOf("#")) sufFix = sufFix.split("#")[0];
			if(sufFix.indexOf("?")) sufFix = sufFix.split("?")[0];
			if(this.fileType[sufFix])
				eval("this."+ this.fileType[sufFix]+"()");
			else
				alert("该格式的文件无法播放");
		}
		else
			alert("请指定要播放的文件");
	}
};