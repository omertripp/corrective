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
 * dyuproject / StandardConvertorCache (examples 41-42 in Ohad's benchmarks)
 *
 */
public class TestCase3 {

	private final Random r = new Random();
	
	private final ConcurrentMap<Key, Value> map = new ConcurrentHashMap<>();
	
	public Value op1(Key key, boolean b) {
		Value value = map.get(key);
		if (value == null && b) {
			value = new Value(r.nextInt());
			map.putIfAbsent(key, value);
		}
		return value;
	}
	
	public Value op2(Key key) {
		Value value = map.get(key);
		if (value == null) {
			value = new Value(r.nextInt());
			map.putIfAbsent(key, value);
		}
		return value;
	}
}
