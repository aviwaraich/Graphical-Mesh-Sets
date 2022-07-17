package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		LinkedHashSet<String> Verticies = new LinkedHashSet<>();
		
		for(Polygon polygon:polygons)
		{
			for(Vertex NewVertex:polygon.vertices)
			{
				Verticies.add("v "+ NewVertex.xcord + " " + NewVertex.ycord + " " + NewVertex.zcord + "\n");
			}
		}
		
		ArrayList<String> Verticies_Array = new ArrayList<>(Verticies);
		LinkedHashSet<String> Faces = new LinkedHashSet<>();
		String Face;
		for(Polygon polygon:polygons)
		{
			Face = "f ";
			for(Vertex NewVertex:polygon.vertices)
			{
				Face = Face + (Verticies_Array.indexOf("v "+ NewVertex.xcord + " " + NewVertex.ycord + " " + NewVertex.zcord + "\n")+1) + " ";
			}
			//System.out.println(Face);
			Faces.add(Face+"\n");
		}
		
		for(String Write_Verticies:Verticies_Array)
		{
			writer.write(Write_Verticies);
		}
		
		for(String Write_Faces:Faces)
		{
			writer.write(Write_Faces);
		}
		
		writer.close();
	}

}
