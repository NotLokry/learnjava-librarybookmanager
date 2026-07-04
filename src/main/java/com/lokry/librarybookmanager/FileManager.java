package com.lokry.librarybookmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager{
    final private String libraryFile = "library.txt";
    final private File file;

    public FileManager(){
        this.file = new File(libraryFile);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveLibrary(Library library){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(library);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public Library loadLibrary(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Library)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof IOException)createFile();
            else e.printStackTrace();
            return new Library(new ArrayList<>());
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void createFile(){
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace();}
    }
}