import java.io.Serializable;

/***
 * @author Julita Bielaniewicz 227080
 */

public class InputType implements Serializable {
	private static final long serialVersionUID = 101L;
	String operation;
	public double x1;
	public double x2;

	public double getx1() {
		return x1;
	}

	public double getx2() {
		return x2;
	}
}
