package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

public class OFFMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		ArrayList<String> List = new ArrayList<>();
		String line;
		int counter = 0;
		Pattern pattern = null;
		int NumofVertx = -1;
		int NumofFaces = 0;
		
		while((line = reader.readLine()) != null)
		{
			line = line.replaceAll("\\s+", " ").trim();
			String[] arrOfStr_First = line.split(" ");
			if (counter==0 && !line.matches("OFF"))
			{
				reader.close();
				throw new WrongFileFormatException("File Violating The Format, The Pattern does not statisfy the hearder");
			} else if(counter>0){
				for(String Dig:arrOfStr_First)
				{
					if(arrOfStr_First.length<5)
					{
						pattern = Pattern.compile("(+-?\\\\d+(\\\\.\\\\d+)?){3} *");
					} else {
						pattern = Pattern.compile("(3 \\d+ \\d+ \\d+ 220 220 220)");
						Dig = line;
					}
					if(!pattern.matcher(Dig).find() || arrOfStr_First.length<3)
					{
						System.out.println(Dig);
						reader.close();
						throw new WrongFileFormatException("File Violating The Format, The Pattern does not statisfy being a Digit accept by off");
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
		
		String[] arrOfStr_Vertx_Faces = List.get(1).split(" ");
		if(!(NumofVertx == Integer.parseInt(arrOfStr_Vertx_Faces[0]) && NumofFaces == Integer.parseInt(arrOfStr_Vertx_Faces[1]))) {throw new WrongFileFormatException("Faces/Vertex Don't Match: " + line);}
		
		String[] arrOfStr = List.get(1).split(" ");
		NumofVertx = Integer.parseInt(arrOfStr[0]);
		//System.out.println(NumofVertx);
		
		HashSet<Polygon> Total_Polygons = new HashSet<Polygon>();
		
		for(int i = NumofVertx+2;i<List.size();i++)
		{
			String[] arrOfStr_F = List.get(i).split(" ");
			LinkedHashSet<Vertex> Matrix = new LinkedHashSet<Vertex>();
			for(int j = 1; j<=Integer.parseInt(arrOfStr_F[0]); j++)
			{
				String[] arrOfStr_V = List.get(Integer.parseInt(arrOfStr_F[j])+2).split(" ");
				Vertex column = new Vertex(Double.parseDouble(arrOfStr_V[0]),Double.parseDouble(arrOfStr_V[1]),Double.parseDouble(arrOfStr_V[2]));
				//System.out.println(column);
				Matrix.add(column);
			}
			Polygon Each_Polygon = new Polygon(Matrix);
			Total_Polygons.add(Each_Polygon);
		}
		return Total_Polygons;
	}

}
