import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    //write to Storage.txt
    public void saveList(List<Task> tasks) throws IOException{ //where does it catch?
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toStorageString() + "\n");
        }
        fw.close();
    }

}
