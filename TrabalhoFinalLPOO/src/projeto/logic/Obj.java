package projeto.logic;


/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public class Obj {

	private Rectangulo dimensions;
	private String path;
	private Rectangulo subImage;

	/**
	 * Construtor da classe Obj
	 * @param dim dimensao do objeto
	 * @param p caminho para a imagem do objecto
	 * @param subI indicacao parametrizada da parte da textura a utilizar
	 */
	public Obj(Rectangulo dim, String p, Rectangulo subI){
		dimensions = dim;
		path = p;
		subImage = subI;
	}

	/**
	 * Funcao get para as dimensoes
	 * @return as dimensoes do obj
	 */
	public Rectangulo getDimensions() {
		return dimensions;
	}

	/**
	 * Funcao set para as dimensoes do obj
	 * @param dimensions novas dimensoes do obj
	 */
	public void setDimensions(Rectangulo dimensions) {
		this.dimensions = dimensions;
	}

	/**
	 * Funcao get para o caminho da textura
	 * @return caminho da textura
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Funcao set para o caminho da textura
	 * @param path novo caminho para a textura
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Funcao get para a subImg do objecto
	 * @return subImg para o objecto
	 */
	public Rectangulo getSubImage() {
		return subImage;
	}

	/**
	 * Funcao set para a sub-imagem do objecto
	 * @param subImage nova sub imagem do objecto
	 */
	public void setSubImage(Rectangulo subImage) {
		this.subImage = subImage;
	}

}