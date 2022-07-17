package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OFFMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		LinkedHashSet<String> Verticies = new LinkedHashSet<>();
		
		for(Polygon polygon:polygons)
		{
			for(Vertex NewVertex:polygon.vertices)
			{
				Verticies.add(NewVertex.xcord + " " + NewVertex.ycord + " " + NewVertex.zcord + "\n");
				System.out.println(NewVertex.xcord + " " + NewVertex.ycord + " " + NewVertex.zcord + "\n");
			}
		}
		
		ArrayList<String> Verticies_Array = new ArrayList<>(Verticies);
		System.out.println(Verticies_Array.size());
		LinkedHashSet<String> Faces = new LinkedHashSet<>();
		String Face;
		for(Polygon polygon:polygons)
		{
			Face = polygon.vertices.size() + " ";
			for(Vertex NewVertex:polygon.vertices)
			{
				Face = Face + (Verticies_Array.indexOf(NewVertex.xcord + " " + NewVertex.ycord + " " + NewVertex.zcord + "\n")) + " ";
			}
			Faces.add(Face+"  220 220 220\n");
		}
		
		writer.write("OFF" + "\n");
		writer.write(Verticies_Array.size() + " " + Faces.size() + " 0\n");
		
		
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
