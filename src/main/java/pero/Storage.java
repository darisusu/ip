package pero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Handles saving to storage, and retrieval/loading from storage
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //write to Pero_storage
    public void saveList(List<Task> tasks) throws IOException { //where does it catch?
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toStorageString() + "\n");
        }
        fw.close();
    }

    private static boolean isMarked(String oneZero) {
        if (oneZero.equals("1")) { //marked
            return true;
        } else if (oneZero.equals("0")) { //unmarked
            return false;
        } else { //wrong
            throw new IllegalArgumentException("Invalid marked value: " + oneZero);
        }
    }

    //load from Pero_storage
    public List<Task> loadList() {
        //new list to store
        List<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath); //what if no filepath
            Scanner s = new Scanner(f); //may throw FileNotFoundException

            while (s.hasNext()) {
                //Get tasks + sort diff cases
                //what if line empty/doesn't start with correct char
                String currTaskLine = s.nextLine();
                if (currTaskLine.isEmpty()) {
                    continue;
                }

                //parse through each line and convert to task to add to tasks
                //char firstChar = currTaskLine.charAt(0); //see what type of pero.Task

                String[] parts = currTaskLine.split(" \\| "); //split the line into parts
                String firstChar = parts[0];

                switch (firstChar) {
                    case "T": { //pero.ToDo
                        if (parts.length != 3) { //wrong format
                            // throw new IllegalArgumentException("Invalid pero.ToDo line in storage file: " + currTaskLine);
                            System.out.println("Skipped wrong format ToDo line: " + currTaskLine);
                            continue;
                        }
                        boolean isDone = isMarked(parts[1]);
                        Task t = new ToDo(parts[2], isDone);
                        tasks.add(t);
                        break;
                    }
                    case "D": { //Deadline
                        if (parts.length != 4) { //wrong format
                            //throw new IllegalArgumentException("Invalid Deadline line in storage file: " + currTaskLine);
                            System.out.println("Skipped invalid Deadline line: " + currTaskLine);
                            continue;
                        }
                        boolean isDone = isMarked(parts[1]);
                        try {
                            LocalDateTime byTimeObj = Task.parseDateTime(parts[3]);
                            Task t = new Deadline(parts[2], isDone, byTimeObj);
                            tasks.add(t);
                        } catch (PeroException e) {
                            System.out.println("Skipped invalid Deadline (date&time format) in storage: "
                                    + currTaskLine );
                        }
                        break;
                    }
                    case "E": { //Event
                        if (parts.length != 5) { //wrong format
                            //throw new IllegalArgumentException("Invalid Event line in storage file: " + currTaskLine);
                            System.out.println("Skipped invalid Event line: " + currTaskLine);
                            continue;
                        }
                        boolean isDone = isMarked(parts[1]);
                        try {
                            LocalDateTime fromTimeObj = Task.parseDateTime(parts[3]);
                            LocalDateTime byTimeObj = Task.parseDateTime(parts[4]);
                            Task t = new Event(parts[2], isDone, fromTimeObj, byTimeObj);
                            tasks.add(t);
                        } catch (PeroException e) {
                            System.out.println("Skipped invalid Event (date&time format) in storage: "
                                    + currTaskLine );
                        }
                        break;
                    }
                    default: // not any of the tasks
                        throw new IllegalArgumentException(
                                "Unknown pero.Task type found: " + firstChar + " in " + currTaskLine);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid task line: " + e);
        }
        return tasks;
    }


}
