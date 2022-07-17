package assignment;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Polygon extends GraphicalObject{
	LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();

	public Polygon(LinkedHashSet<Vertex> vertices) {
		super();
		this.vertices = vertices;
		for (Vertex NewVertex:vertices) {
			vertices.add(NewVertex);
		}
	}

	@Override
	public void transform(double[][] matrix) { 
		for (Vertex vert : vertices) {
			vert.transform(matrix);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertices);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polygon other = (Polygon) obj;
		return Objects.equals(vertices, other.vertices);
	}
	
	
	
	

}
