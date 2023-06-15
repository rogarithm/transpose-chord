package service.util;

public class FileHandlerFactory {

    public static FileHandler create(String pathName, String fileName) {
        return new FileHandler(pathName, fileName);
    }

}