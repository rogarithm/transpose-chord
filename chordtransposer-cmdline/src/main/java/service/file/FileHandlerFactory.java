package service.file;

public class FileHandlerFactory {

    public static DefaultFileHandler create(String pathName, String fileName) {
        return new FileHandler(pathName, fileName);
    }

}