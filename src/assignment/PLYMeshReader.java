package assignment;

import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> List = new ArrayList<>();
		String[] regex = {"ply"
				,"format ascii 1.0"
				,"element vertex [\\d]+"
				,"property float32 x"
				,"property float32 y"
				,"property float32 z"
				,"element face [\\d]+"
				,"property list uint8 int32 vertex_indices"
				,"end_header"};
		Pattern pattern = null;
		int NumofVertx = 0;
		int NumofFaces = 0;
		String line;
		int counter = 0;
		while((line = reader.readLine()) != null)
		{
			line = line.replaceAll("\\s+", " ").trim();
			String[] arrOfStr_First = line.split(" ");
			if (counter<9 && !line.matches(regex[counter]))
			{
				reader.close();
				throw new WrongFileFormatException("File Violating The Format, The Pattern does not statisfy the hearder");
			} else if(counter>=9){
				for(String Dig:arrOfStr_First)
				{
					pattern = Pattern.compile("^[+-]?[0-9]+(?:\\.[0-9]+)?$");
					if(!pattern.matcher(Dig).find())
					{
						reader.close();
						throw new WrongFileFormatException("File Violating The Format, The Pattern does not statisfy the given Character as an number (Double/Int)");
					}
				}
				if(arrOfStr_First.length>3)
				{
					NumofFaces++;
				} else {
					NumofVertx++;
				}
			}
			List.add(line);
			counter++;
		}
		reader.close();
		
		String[] arrOfStr_Vertx = List.get(2).split(" ");
		String[] arrOfStr_Faces = List.get(6).split(" ");
		
		if(!(NumofVertx == Integer.parseInt(arrOfStr_Vertx[2]) && NumofFaces == Integer.parseInt(arrOfStr_Faces[2]))) {throw new WrongFileFormatException("Faces/Vertex Don't Match given and provided");}
		HashSet<Polygon> Total_Polygons = new HashSet<Polygon>();
		 
		for(int i = NumofVertx+9;i<List.size();i++)
		{
			String[] arrOfStr_F = List.get(i).split(" ");
			LinkedHashSet<Vertex> Matrix = new LinkedHashSet<Vertex>();
			for(int j = 1; j<=Integer.parseInt(arrOfStr_F[0]); j++)
			{
				if((Integer.parseInt(arrOfStr_F[j])) < NumofVertx && Integer.parseInt(arrOfStr_F[j])>=0)
				{
					String[] arrOfStr_V = List.get(Integer.parseInt(arrOfStr_F[j])+9).split(" ");
					Vertex column = new Vertex(Double.parseDouble(arrOfStr_V[0]),Double.parseDouble(arrOfStr_V[1]),Double.parseDouble(arrOfStr_V[2]));
					Matrix.add(column);
				} else {
					throw new WrongFileFormatException("File Violating The Format at because the Number of Given Vertex and Index don't Match");
				}
			}
			Polygon Each_Polygon = new Polygon(Matrix);
			
			Total_Polygons.add(Each_Polygon);
		}
			
		return Total_Polygons;
	}

}
