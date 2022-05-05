import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Playsong {
    Playlistoperations plo=new Playlistoperations();
    List<Playlist> list;
    Hashtable<Integer, String> song = new Hashtable<>();
    Long currentFrame;
    Clip clip;
    AudioInputStream audioInputStream;
    int count=1;

        public Playsong()
        {
            list=plo.fetchplaylist();
        }
        public void getsongnamefromplaylist(String name) {

            List<Playlist> items=null;
            Optional<Playlist> check1 = list.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name))).findAny();
            if(check1.isPresent())
            {
                System.out.println("--------------------Your list has----------------");
                list.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name))).forEach(System.out::println);
                items=list.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name))).collect(Collectors.toList());
            }
            else
            {
                System.out.println("No Playlist available.");
                plo.creatplaylist(name);
            }
            if(items.size()!=0) {
                int size = 1;
                for (int i = 0; i < items.size(); i++) {
                    song.put(size, items.get(i).getTrack_name());
                    size++;
                }
                for (Map.Entry<Integer, String> e : song.entrySet()) {
                    System.out.println("Enter " + e.getKey() + " for Playing " + e.getValue());
                }
            }
        }
        public void getsongchoice(int choice) throws UnsupportedAudioFileException, IOException, LineUnavailableException
        {
            if(choice>=1 && choice<=song.size()) {
                String song1;
                song1 = song.get(choice);
                if(song1!=null) {
                    count=choice;
                    System.out.println("Enjoy the song " + song1);
                    filepath(song1);
                }
            }
            else
            {
                System.out.println("No Songs to Play");
            }
        }
        private void filepath(String song) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

            audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\java_with_vsc\\audio_file\\" + song + ".wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
    public void playsong(int c) throws UnsupportedAudioFileException, IOException, LineUnavailableException
            {
                        switch (c) {
                            case 0:
                                play();
                                break;
                            case 1:
                                pause();
                                break;
                            case 2:
                                restart();
                                break;
                            case 3:
                                stop();
                                break;
                            case 4:
                                next();
                                break;
                            case 5:
                                prev();
                                break;
                        }
                    }

        public void play()
        {
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }

        public void pause()
        {
            this.currentFrame = this.clip.getMicrosecondPosition();
            clip.stop();
        }

        public void restart()
        {
            clip.setMicrosecondPosition(0);
        }

    public void next() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        count=count+1;
        getsongchoice(count);
    }
    public void prev() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        count=count-1;
        getsongchoice(count);
    }
        public void stop()
        {
            currentFrame = 0L;
            clip.stop();
            clip.close();
        }

    }
