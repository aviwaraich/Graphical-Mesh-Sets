package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter{

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
			//System.out.println(Face);
			Faces.add(Face+"\n");
		}
		
		writer.write("ply" + "\n");
		writer.write("format ascii 1.0" + "\n");
		writer.write("element vertex "+ Verticies_Array.size() + "\n");
		writer.write("property float32 x" + "\n");
		writer.write("property float32 y" + "\n");
		writer.write("property float32 z" + "\n");
		writer.write("element face "+ (Faces.size()) + "\n");
		writer.write("property list uint8 int32 vertex_indices" + "\n");
		writer.write("end_header" + "\n");
		
		
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
