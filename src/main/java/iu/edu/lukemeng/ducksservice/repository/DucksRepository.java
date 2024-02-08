package iu.edu.lukemeng.ducksservice.repository;



import iu.edu.lukemeng.ducksservice.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DucksRepository {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME = "ducks_database.txt";

    private String IMAGES_FOLDER_PATH = "ducks/images/";

    private static final Path IMAGE_DIRECTORY = Paths.get("images");
    private static final Path AUDIO_DIRECTORY = Paths.get("audio");

    public DucksRepository() {
        File ducksImagesDirectory = new File("ducks/images");
        if(!ducksImagesDirectory.exists()) {
            ducksImagesDirectory.mkdirs();
        }
        File ducksAudioDirectory = new File("ducks/audio");
        if(!ducksAudioDirectory.exists()) {
            ducksAudioDirectory.mkdirs();
        }
    }
    private static void appendToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public boolean add(DuckData duckData) throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        String data = duckData.toLine();
        appendToFile(path, data + NEW_LINE);
        return true;
    }



    public DuckData getDuck(int id) throws IOException {
        List<DuckData> ducks = findAll();
        for(DuckData duck : ducks) {
            if (duck.id() == id) {
                return duck;
            }
        }
        return null;
    }

    public List<Duck> getAllDucks() throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        List<Duck> ducks = new ArrayList<>();

        for (String line : data) {
            String[] words = line.split(",");


            int duckId = Integer.parseInt(words[0]);
            DuckType duckType = DuckType.valueOf(words[1].trim().toUpperCase());



            Duck duck = createDuck(duckType, duckId);
            if (duck != null) {

                ducks.add(duck);
            }
        }
        return ducks;
    }
    public List<DuckData> findAll() throws IOException {
        List<DuckData> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line : data) {
            DuckData d = DuckData.fromLine(line);
            result.add(d);
        }
        return result;
    }

    public List<DuckData> search(String type) throws IOException {
        List<DuckData> ducks = findAll();
        List<DuckData> result = new ArrayList<>();
        for(DuckData duck : ducks) {
            if (type != null && !duck.type().equalsIgnoreCase(type)) {
                continue;
            }
            result.add(duck);
        }
        return result;
    }

    // from prof sample code
    public boolean updateImage(int id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        System.out.println("The file " + path + " was saved successfully.");
        file.transferTo(path);
        return true;
    }

    public boolean uploadImage(int id, MultipartFile file) throws IOException {
        String fileExtension = ".png";
        Path directoryPath = Paths.get(IMAGES_FOLDER_PATH).toAbsolutePath();
        Files.createDirectories(directoryPath); // Ensure the directory exists

        Path filePath = directoryPath.resolve(id + fileExtension);
        System.out.println("The file " + filePath + " was saved successfully.");
        file.transferTo(filePath);
        return true;
    }


    public byte[] downloadImage(int duckId, String filename) throws IOException {
        Path imagePath = IMAGE_DIRECTORY.resolve(duckId + "_" + filename);
        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        }
        return null;
    }

    public boolean uploadAudio(MultipartFile audio, int duckId) throws IOException {
        Path audioPath = AUDIO_DIRECTORY.resolve(duckId + "_" + audio.getOriginalFilename());
        Files.createDirectories(audioPath.getParent());
        Files.copy(audio.getInputStream(), audioPath, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    public byte[] downloadAudio(int duckId, String filename) throws IOException {
        Path audioPath = AUDIO_DIRECTORY.resolve(duckId + "_" + filename);
        if (Files.exists(audioPath)) {
            return Files.readAllBytes(audioPath);
        }
        return null;
    }

    private Duck createDuck(DuckType type, int id) {
        switch (type) {
            case MALLARD:
                return new Mallard(id, type);
            case REDHEAD:
                return new Redhead(id, type);
            case RUBBER:
                return new RubberDuck(id, type);
            case DECOY:
                return new DecoyDuck(id, type);
            default:
                return null;
        }
    }
}
