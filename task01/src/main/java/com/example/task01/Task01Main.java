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
        Process process = new ProcessBuilder(
                "ffprobe",
                "-v", "error",
                "-show_format",
                "-of", "flat",
                file.getAbsolutePath()
        ).start();
        String result = null;
        String prefix = "format.tags.title=";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(prefix)) {
                    String rawTitle = line.substring(prefix.length());
                    result = rawTitle.replace("\"", "");
                    break;
                }
            }
        }

        process.waitFor();
        return result;
    }
}
