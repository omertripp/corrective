/**
 * 
 */
package permute.bench;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author omertripp
 * 
 * Apache Tomcat / ApplicationContext (examples 20-21 in Ohad's benchmarks)
 *
 */
public class TestCase2 {

	private final ConcurrentMap<Key, Value> map = new ConcurrentHashMap<>();
	
	public Value op1(Key key) {
		Value result = null;
		boolean found = map.containsKey(key);
		if (found) {
			result = map.get(key);
			map.remove(key);
		}
		return result;
	}
	
	public boolean op2(Key key, Value value) {
		boolean replaced = false;
		Value oldvalue = map.get(key);
		if (oldvalue != null)
			replaced = true;
		map.put(key, value);
		return replaced;
	}
}
