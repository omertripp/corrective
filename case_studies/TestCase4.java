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
 * Flexive / FxValueRendererFactory (examples 51-52 in Ohad's benchmarks)
 *
 */
public class TestCase4 {

	private final Random r = new Random();
	
	private final ConcurrentMap<Key, Value> map = new ConcurrentHashMap<>();
	
	public Value op1(Key key) {
		if (!map.containsKey(key)) {
			map.putIfAbsent(key, new Value(r.nextInt()));
		}
		return map.get(key);
	}
	
	public void op2(Key key) {
		map.putIfAbsent(key, new Value(r.nextInt()));
		Value value = map.get(key);
		f(value);
	}
	
	public void f(Value value) {
		// Do nothing...
	}
}