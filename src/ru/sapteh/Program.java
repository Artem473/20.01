package ru.sapteh;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Program {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("C:/fgf/Registration");
        Path source1 = Paths.get("C:/fgf/Registration.zip");
        FileInputStream zipArchive = new FileInputStream(source1.toFile());
        ZipInputStream zip = new ZipInputStream(zipArchive);


        ZipEntry zipEntry;
        ZipFile zipFile = new ZipFile(source1.toString());
        List<ZipEntry> zipEntryList = new ArrayList<>();
        while ((zipEntry = zip.getNextEntry()) != null){
            zipEntryList.add(zipEntry);
        }
       // System.out.println("Файл был создан и занесен");



        for (ZipEntry entry : zipEntryList) {
            Path pathad = path(Paths.get(entry.toString()),source);
            if (entry.isDirectory() && Files.notExists(pathad)){
                Files.createDirectory(pathad);

            }else if (Files.notExists(pathad)){
                Files.copy(zipFile.getInputStream(entry),pathad);
            }
        }


           zip.close();
    }
    public static Path path (Path pathzip ,Path absolutPath){
        return absolutPath.resolve(pathzip);

    }
}
