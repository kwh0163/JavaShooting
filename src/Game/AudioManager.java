package Game;

import javax.sound.sampled.*;

import Util.AudioType;
import Util.Debug;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioManager {
	private final ExecutorService soundPool = Executors.newFixedThreadPool(1000);
	
	enum BGMType{
		Stage1,
		Stage1Boss
	}
	
	private Clip bgmClip;
	private HashMap<BGMType, String> bgmMap;
	private HashMap<AudioType, String> audioMap;
	
	public AudioManager(){
		try {
			bgmClip = AudioSystem.getClip();
		}
		catch (Exception e) {
			Debug.Log("getting Clip Error");
		}
		
		bgmMap = new HashMap<BGMType, String>();
		bgmMap.put(BGMType.Stage1, "Stage1-1BGM.wav");
		
		audioMap = new HashMap<AudioType, String>();
		audioMap.put(AudioType.PlayerHit, "PlayerHit.wav");
		audioMap.put(AudioType.PlayerShoot,"PlayerShoot.wav");
		audioMap.put(AudioType.EnemyHit, "EnemyHit.wav");
		audioMap.put(AudioType.EnemyShoot0, "tan00.wav");
		audioMap.put(AudioType.EnemyShoot1, "tan01.wav");
		audioMap.put(AudioType.EnemyShoot2, "tan02.wav");
		audioMap.put(AudioType.NormalEnemyDie, "NormalEnemyDie.wav");
		audioMap.put(AudioType.PowerUp, "PowerUp.wav");
		audioMap.put(AudioType.BossDie, "BossDie.wav");
		
	}
	
	public void PlayBGM(int stageNumber) {
		try {
			bgmClip.flush();         // 버퍼 초기화
	        bgmClip.setFramePosition(0);  // 시작 프레임을 0으로 설정
			 
			switch (stageNumber) {
			case 1:
				bgmClip.open(GetBGM(BGMType.Stage1));
				break;
			case 11:
				bgmClip.open(GetBGM(BGMType.Stage1Boss));
			default:
				break;
			}
			bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
			bgmClip.start();
		}
		catch (Exception e) {
			Debug.Log(e.toString());
		}
	}
	public void StopBGM() {
		if(bgmClip != null && bgmClip.isRunning())
			bgmClip.stop();
	}

    public void PlaySound(AudioType audioType, float volume) {
    	if(!audioMap.containsKey(audioType)) {
    		Debug.Log("No AudioType : " + audioType.toString());
    		return;
    	}
        soundPool.execute(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(GetAudio(audioType));
                
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(volumeControl, volume);
                
                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        soundPool.shutdown(); // 모든 스레드 종료
    }
    
    private AudioInputStream GetAudio(AudioType audioType) {
    	File audioFile = new File("Sound\\" + audioMap.get(audioType));
    	try {
    		return AudioSystem.getAudioInputStream(audioFile);
    	}
    	catch (Exception e) {
			Debug.Log(e.toString());
			return null;
		}
    }
    private AudioInputStream GetBGM(BGMType bgmType) {
    	File audioFile = new File("Sound\\" + bgmMap.get(bgmType));
    	try {
    		return AudioSystem.getAudioInputStream(audioFile);
    	}
    	catch (Exception e) {
			Debug.Log(e.toString());
			return null;
		}
    }
    
    private void setVolume(FloatControl volumeControl, float volume) {
        // volume: 0.0 (mute) to 1.0 (max volume)
        float min = volumeControl.getMinimum(); // 최소 데시벨 값
        float max = volumeControl.getMaximum(); // 최대 데시벨 값
        float newVolume = min + (max - min) * volume;
        volumeControl.setValue(newVolume);
    }
}
