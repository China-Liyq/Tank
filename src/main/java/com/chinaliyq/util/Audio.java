package com.chinaliyq.util;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/9 - 19:31
 * @Version: 1.0
 **/
public class Audio extends Thread {
    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;
    private AudioInputStream audioInputStream = null;

    private final int LENGTH = 1024 * 1024 * 15;
    private byte[] bytes = new byte[LENGTH];
    private int len;

    public Audio(String fileName) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine= (SourceDataLine) AudioSystem.getLine(dataLine_info);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, bytes.length);
            sourceDataLine.start();
            while ((len = audioInputStream.read(bytes)) > 0){
                sourceDataLine.write(bytes,0,len);
            }
            audioInputStream.close();
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop(){
        try {
            while (true){
                len = 0;
                sourceDataLine.open(audioFormat, LENGTH);
                sourceDataLine.start();
                audioInputStream.mark(12358946);
                while ((len = audioInputStream.read(bytes))>0){
                    sourceDataLine.write(bytes,0,len);
                }
                audioInputStream.reset();
                sourceDataLine.drain();
                sourceDataLine.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
//        byte[] bytes = new byte[1024 * 5];
        try {
            len = 0;
            sourceDataLine.open(audioFormat, LENGTH);
            sourceDataLine.start();
            audioInputStream.mark(12358946);
            while ((len = audioInputStream.read(bytes)) > 0){
                sourceDataLine.write(bytes,0,len);
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
