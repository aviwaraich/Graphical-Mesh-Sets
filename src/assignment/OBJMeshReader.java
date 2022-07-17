package assignment;

import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException,Exception{
		
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			ArrayList<String> List = new ArrayList<>();
			Pattern pattern = Pattern.compile("^[v](\\s[-]?([0-9]*[.])?[0-9]+){3}");
			String line;
			int total_V = 0;
			
			while((line = reader.readLine()) != null)
			{
				line = line.replaceAll("\\s+", " ").trim();
				String[] arrOfStr_First = line.split(" ");
				if(arrOfStr_First[0].equals("f"))
				{ 
					pattern = Pattern.compile("^[f](\\s[0-9]+){3}");
					total_V = total_V - 1;
				}
				if(pattern.matcher(line).find() && total_V >=0)
				{
					total_V = total_V + 1;
					List.add(line);
				} else {
					System.out.println("here: " + line);
					reader.close();
					throw new WrongFileFormatException("File Violating The Format");
				}
			}
			reader.close();
			
			LinkedHashSet<Polygon> Total_Polygons = new LinkedHashSet<Polygon>();
			
			for(String EchLine:List)
			{
				String[] arrOfStr = EchLine.split(" ");
				if(arrOfStr[0].equals("f"))
				{
					LinkedHashSet<Vertex> Matrix = new LinkedHashSet<Vertex>();
					for(int i = 1; i<arrOfStr.length; i++)
					{
						if(Integer.parseInt(arrOfStr[i]) <= total_V &&Integer.parseInt(arrOfStr[i])>0)
						{
							String[] arrOfStr_V = List.get(Integer.parseInt(arrOfStr[i])-1).split(" ");
							Vertex column = new Vertex(Double.parseDouble(arrOfStr_V[1]),Double.parseDouble(arrOfStr_V[2]),Double.parseDouble(arrOfStr_V[3]));
							Matrix.add(column);
						} else {
							throw new WrongFileFormatException("File Violating The Format Index error with the Faces");
						}
					}
					
					Polygon Each_Polygon = new Polygon(Matrix);
					Total_Polygons.add(Each_Polygon);
				}
			}
		return Total_Polygons;
	}

}
