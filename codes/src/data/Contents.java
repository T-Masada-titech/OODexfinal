package data;
import java.util.Map;
import java.util.TreeMap;
public class Contents extends TreeMap<String, Integer>{
	public String toString() {
		String out = "\n";
		for(Map.Entry<String, Integer> e : this.entrySet()) {
			out = out + e.getKey() + " : " + e.getValue().toString() + "\n";
		}
		return out;
	}
}

