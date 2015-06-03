/**
 * 
 */
package permute.bench;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author omertripp
 * 
 * Apache Cassandra / SuperColumn (examples 7-8 in Ohad's benchmarks)
 *
 */
public class TestCase1 {

	private final Random r = new Random();
	
	private final ConcurrentMap<Key, Value> map = new ConcurrentHashMap<>();
	
	public Value op1(Key key, Value value) {
		Value w = map.putIfAbsent(key, value);
		if (w != null) {
			while (!map.replace(key, w, value)) {
				w = map.get(key);
				value = new Value(r.nextInt());
			}
		}
		return w;
	}
	
	public Value op2(Key key, Value value) {
		Value w = map.putIfAbsent(key, value);
		if (w != null) {
			while (true) {
				if (map.replace(key, w, value)) 
					break;
				w = map.get(key);
			}
		}
		return w;
	}
}
