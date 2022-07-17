package assignment;

import java.util.HashSet;
import java.util.Objects;

public class Mesh extends GraphicalObject{
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader(MeshReader reader)
	{
		this.reader = reader;
	}
	
	public void setWriter(MeshWriter writer)
	{
		this.writer = writer;
	}
	
	public void readFromFile(String filename) throws Exception
	{
		polygons = reader.read(filename);
	}
	
	public void writeToFile(String filename) throws Exception
	{
		writer.write(filename, polygons);
	}
	
	@Override
	public void transform(double[][] matrix) {
		for(Polygon polygon:polygons)
		{
			polygon.transform(matrix);
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(polygons);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesh other = (Mesh) obj;
		return Objects.equals(polygons, other.polygons);
	}

}