package BigHomework;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Musics {
	URL url;
	AudioClip ac;
	
	@SuppressWarnings("deprecation")
	void playgo(String path){
		File f1 = new File(path);
		   try {
		    url= f1.toURL();
		    ac= Applet.newAudioClip(url);
		    ac.play();
		} catch (MalformedURLException e) {
		e.printStackTrace();
		} 
	}
	@SuppressWarnings("deprecation")
	void loopgo(String path){
		File f1 = new File(path);
		   try {
		    url= f1.toURL();
		    ac= Applet.newAudioClip(url);
		    ac.loop();
		} catch (MalformedURLException e) {
		e.printStackTrace();
		} 
	}
	public void stopmusic() {
		ac.stop();
	}
}
