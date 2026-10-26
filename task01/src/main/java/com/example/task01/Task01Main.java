package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\Users\\User\\Downloads\\ffmpeg-master-latest-win64-gpl\\"
                + "ffmpeg-master-latest-win64-gpl\\bin\\ffprobe.exe",
                "-v", "error", "-show_format", "-of", "flat", file.getAbsolutePath());

        Process process = processBuilder.start();
        String result = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("format.tags.title=\"")) {
                    result = line.substring(19, line.length() - 1);
                    break;
                }
            }
        }

        process.waitFor();
        return result;
    }
}
