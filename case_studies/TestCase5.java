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
 * Adobe BlazeDS / FIFOMessageQueue (examples 59-60 in Ohad's benchmarks)
 *
 */
public class TestCase5 {

	private final Random r = new Random();
	
	private final ConcurrentMap<Key, Value> map = new ConcurrentHashMap<>();
	
	public Value op1(Key key) {
		Value value = map.get(key);
		if (value == null) {
			value = new Value(r.nextInt());
			map.put(key, value);
		}
		return f1(value);
	}
	
	private Value f1(Value value) {
		// Do nothing...
		return value;
	}

	public void op2(Key key) {
		Value value = map.get(key);
		if (value == null) {
			value = new Value(r.nextInt());
			map.put(key, value);
		}
		f2(value);
	}

	private void f2(Value value) {
		// Do nothing...
	}
}