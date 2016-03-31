package ThreadTest02;

import java.io.File;
import java.util.HashMap;

public interface Task {
	public HashMap<String,Integer> execute(File file);
}
