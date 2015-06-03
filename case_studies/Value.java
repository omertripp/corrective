/**
 * 
 */
package permute.bench;

/**
 * @author omertripp
 *
 */
public class Value {

	private final int value;
	
	public Value(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		if (value != other.value)
			return false;
		return true;
	}
}
