package projeto.logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public class Obj {

	private Rectangulo dimensions;
	private String path;
	private Rectangulo subImage;

	public Obj(Rectangulo dim, String p, Rectangulo subI){
		dimensions = dim;
		path = p;
		subImage = subI;
	}

	public Rectangulo getDimensions() {
		return dimensions;
	}

	public void setDimensions(Rectangulo dimensions) {
		this.dimensions = dimensions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Rectangulo getSubImage() {
		return subImage;
	}

	public void setSubImage(Rectangulo subImage) {
		this.subImage = subImage;
	}

}