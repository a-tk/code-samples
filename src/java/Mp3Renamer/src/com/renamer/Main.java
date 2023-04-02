package com.renamer;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Main {
    //need these for the Tika library
    private static AutoDetectParser parser;
    private static BodyContentHandler handler;
    private static Metadata metadata;

    //Fill the metadata object with the data from the file
    //this just handles the file io safely
    private static void getMetaData (File file) {
        if (file.exists() || file.isFile() || file.canRead()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(file);
                parser.parse(stream, handler, metadata);

            } catch (FileNotFoundException fnf) {
                System.err.println(fnf.getMessage());
            } catch (IOException io){
                System.err.println(io.getMessage());
            } catch (SAXException se) {
                System.err.println(se.getMessage());
            } catch (TikaException te) {
                System.err.println(te.getMessage());
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    //special characters that are not allowed in a filename are replaced here
    //this method is slow because strings are immutable, so a new string is created for each replace
    private static String replaceChars (String string) {
        if (string == null) string = "null";
        string = string.replace(":", "");
        string = string.replace("\\", "");
        string = string.replace("/", "");
        string = string.replace("*", "");
        string = string.replace("?", "");
        string = string.replace("<", "");
        string = string.replace(">", "");
        string = string.replace("|", "");
        string = string.replace("\"", "");
        if (string.endsWith(" ")) {
            string = string.substring(0, string.lastIndexOf(' '));
        }

        return string;
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Main \"path/to/files\"");
            System.exit(-1);
        }

        File theDirectory = new File(args[0]);

        if (!theDirectory.exists() || !theDirectory.isDirectory()) {
            System.out.println("The directory does not exist, or is not a directory");
            System.exit(-1);
        }

        int fileNumberInspected = 0;
        int fileNamesUpdated = 0;

        File[] fileList = theDirectory.listFiles();

        Hashtable<String, Integer> fileTypeList = new Hashtable<>();

        parser = new AutoDetectParser();
        handler = new BodyContentHandler(Integer.MAX_VALUE);

        int fileListProgressPercent = fileList.length / 20; // this gives 5% of what is in the list

        //setup complete, now lets start working
        for (int i = 0; i < fileList.length; i++) {
            metadata = new Metadata(); // always clear the previous meta data

            if (i % fileListProgressPercent == 0) {
                //the following 5 is correspondent to the / 20 above
                System.out.println(((i / fileListProgressPercent) * 5) + "% complete");
            }

            fileNumberInspected++;
            File file = fileList[i];

            if (file.isDirectory()) continue; // if the file is a directory, skip it.

            String filename = file.getName();
            String fileType = filename.substring(filename.lastIndexOf('.'));

            getMetaData(file);

            //System.out.println(metadata.toString()); //uncomment this to see available fields
            String trackNumString = metadata.get("xmpDM:trackNumber");

            if (trackNumString == null) {
                trackNumString = "";
            }

            //string is in format of 1/12 or something
            if (trackNumString.indexOf('/') != -1) {
                trackNumString = trackNumString.substring(0, trackNumString.indexOf('/'));

            }
            String artist = metadata.get("meta:author");
            String album = metadata.get("xmpDM:album");
            String song = metadata.get("title");

            //want to parse the track number, and put it before the new filename, so that they will be organized lexicographically
            try {
                int trackNum = Integer.parseInt(trackNumString);
                if (trackNum < 10) {
                    //this supports keeping track 0-99 in lexicographic order by padding values less than 10 with a 0
                    trackNumString = 0 + trackNumString;

                }
            } catch (Exception e) {
                //System.err.println("Error parsing track number of file " + filename);
                //System.err.println(e.getMessage());
            }


            if (song == null) {
                //if the song name isn't defined in the metadata, use the original filename
                song = filename;
            }

            artist = replaceChars(artist);
            album = replaceChars(album);
            song = replaceChars(song);

            String newFilePath = file.getParent() +File.separator + artist + File.separator + album + File.separator;
            String newFilename;
            if (!trackNumString.equals("")) {
                newFilename = newFilePath + trackNumString + "-" + song + fileType;
            } else {
                newFilename = newFilePath + song + fileType;
            }

            File newFilePathFile = new File(newFilePath);
            newFilePathFile.mkdirs(); // create dirs if necessary
            File renamedFile = new File (newFilename);

            Path origPath = file.toPath();
            Path toPath = renamedFile.toPath();

            try {
                Files.move(origPath, toPath);
                fileNamesUpdated++;
            } catch (IOException e) {
                if (e instanceof FileAlreadyExistsException) {
                    System.out.println("File already exists, it is safe to delete this one...");
                }
                System.err.println(e.toString());
                //e.printStackTrace();
                //System.err.println(e.getMessage());
            }

            //Update the statistics
            Integer numberOfOccurrences = fileTypeList.get(fileType);
            if (numberOfOccurrences == null) {
                numberOfOccurrences = 0;
            }
            fileTypeList.put(fileType, numberOfOccurrences + 1);
        }

        //output statistics
        int numberOfFilesInPath = fileList.length;
        System.out.println("Found " + numberOfFilesInPath + " files in the path, and inspected " +
                fileNumberInspected + " of them");
        System.out.println("Updated " + fileNamesUpdated + " files");

        System.out.println("FileTypes detected:");
        Set<Map.Entry<String, Integer>> entrySet = fileTypeList.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println(entry.getKey() + ", " +entry.getValue());
        }

    }
}
