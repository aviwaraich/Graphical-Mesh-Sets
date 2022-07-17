package assignment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {
	//Vertex Test
	@Test
	void test1() {
		Vertex v = new Vertex(0, 0, 0);
		assertTrue(v.xcord==0 && v.ycord==0 && v.zcord==0);
	}
	
	//OBJ Mesh
	@Test
	void test2() throws Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		try {
			mesh.readFromFile("./OBJ1.obj");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format"));
		}
	}

	@Test
	void test3() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("./OBJ2.obj");
		mesh.setWriter(new OBJMeshWriter()); 
		mesh.writeToFile("./OBJ2_rotated.obj"); 
		
		Mesh newmesh = new Mesh();
		newmesh.setReader(new OBJMeshReader());
		newmesh.readFromFile("./OBJ2_rotated.obj");
		
		assertTrue(mesh.equals(newmesh));
	}
	
	@Test
	void test4() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		try {
			mesh.readFromFile("./OBJ3.obj");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format Index error with the Faces"));
		}
	}
	
	//PLY Mesh
	@Test
	void test5() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		try {
			mesh.readFromFile("./PLY1.ply");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format, The Pattern does not statisfy the hearder"));
		}
	}
	
	@Test
	void test6() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("./PLY2.ply");
		mesh.setWriter(new PLYMeshWriter()); 
		mesh.writeToFile("./PLY2_rotated.ply"); 
		
		Mesh newmesh = new Mesh();
		newmesh.setReader(new PLYMeshReader());
		newmesh.readFromFile("./PLY2_rotated.ply");
		
		assertTrue(mesh.equals(newmesh));
	}
	
	@Test
	void test7() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		try {
			mesh.readFromFile("./PLY3.ply");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format, The Pattern does not statisfy the given Character as an number (Double/Int)"));
		}
	}
	
	@Test
	void test8() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		try {
			mesh.readFromFile("./PLY4.ply");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format at because the Number of Given Vertex and Index don't Match"));
		}
	}
	
	@Test
	void test9() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		try {
			mesh.readFromFile("./PLY5.ply");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("Faces/Vertex Don't Match given and provided"));
		}
	}
	
	//OFF Mesh
	@Test
	void test10() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		try {
			mesh.readFromFile("./OFF1.off");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format, The Pattern does not statisfy being a Digit accept by off"));
		}
	}
	
	@Test
	void test11() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("./OFF2.off");
		mesh.setWriter(new OFFMeshWriter()); 
		mesh.writeToFile("./OFF2_rotated.off"); 
		
		Mesh newmesh = new Mesh();
		newmesh.setReader(new OFFMeshReader());
		newmesh.readFromFile("./OFF2_rotated.off");
		
		assertTrue(mesh.equals(newmesh));
	}
	
	@Test
	void test12() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		try {
			mesh.readFromFile("./OFF3.off");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format, The Pattern does not statisfy the hearder"));
		}
	}
	
	//OFF Mesh
	@Test
	void test13() throws Exception,WrongFileFormatException {
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("./car.obj"); 
		mesh.rotateXAxis(Math.PI/3); 
		mesh.rotateYAxis(Math.PI/3);
		mesh.rotateZAxis(Math.PI/3);
		mesh.setWriter(new OBJMeshWriter()); 
		mesh.writeToFile("./car_rotated.obj");
		
		Mesh newmesh = new Mesh();
		newmesh.setReader(new OBJMeshReader());
		try {
			newmesh.readFromFile("./car_rotated.obj");
		} catch (WrongFileFormatException e) {
			assertTrue(e.Errorx.equals("File Violating The Format")); //bc of the -3.201458370993837E-4
		}
	}
}
