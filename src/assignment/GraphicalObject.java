package assignment;

abstract class GraphicalObject {
	public abstract void transform(double[][] matrix);
	
	public void rotateXAxis(double theta) {
		double [][] Rx = {{1,0,0},
						  {0,Math.cos(theta),-Math.sin(theta)},
						  {0,Math.sin(theta),Math.cos(theta)}};
		this.transform(Rx);
	}
	
	public void rotateYAxis(double theta) {
		double [][] Ry = {{Math.cos(theta),0,Math.sin(theta)},
				  		  {0,1,0},
				  		  {-Math.sin(theta),0,Math.cos(theta)}};
		this.transform(Ry);
	}
	
	public void rotateZAxis(double theta) {
		double [][] Rz = {{Math.cos(theta),-Math.sin(theta),0},
		  		  		  {Math.sin(theta),Math.cos(theta),0},
		  		  		  {0,0,1}};
		this.transform(Rz);
	}
}