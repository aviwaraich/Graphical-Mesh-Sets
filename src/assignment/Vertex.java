package assignment;

import java.util.Objects;

public class Vertex extends GraphicalObject{
	public double xcord;
	public double ycord;
	public double zcord;
	
	public Vertex(double xcord, double ycord, double zcord) {
		super();
		this.xcord = xcord;
		this.ycord = ycord;
		this.zcord = zcord;
	}

	@Override
	public void transform(double[][] matrix) {
		double vertex_v[] = {0,0,0};
		vertex_v[0] = matrix[0][0]*xcord + matrix[0][1]*ycord + matrix[0][2]*zcord;
		vertex_v[1] = matrix[1][0]*xcord + matrix[1][1]*ycord + matrix[1][2]*zcord;
		vertex_v[2] = matrix[2][0]*xcord + matrix[2][1]*ycord + matrix[2][2]*zcord;
		xcord = vertex_v[0];
		ycord = vertex_v[1];
		zcord = vertex_v[2];
	}

	@Override
	public int hashCode() {
		return Objects.hash(xcord, ycord, zcord);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return Double.doubleToLongBits(xcord) == Double.doubleToLongBits(other.xcord)
				&& Double.doubleToLongBits(ycord) == Double.doubleToLongBits(other.ycord)
				&& Double.doubleToLongBits(zcord) == Double.doubleToLongBits(other.zcord);
	}

	@Override
	public String toString() {
		return "x=" + xcord + " y=" + ycord + " z=" + zcord;
	}	

}