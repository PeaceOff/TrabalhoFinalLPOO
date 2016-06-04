package projeto.logic;

/**
 * @author João e David
 * @version 1.0
 * @created 03-mai-2016 15:30:30
 */
public class CircleCollider extends Collider {

	private double radius;
	private double elasticity;

	/**
	 * Construtor Cria um circle collider na posicao 0,0
	 * @param r raio do circulo
	 */
	public CircleCollider(double r){
		radius = r;
	} 
	/**
	 * Construtor Circle Collider
	 * @param r raio do circulo
	 * @param e valor da elasticidade
	 * @param pos posicao
	 * @param t tag
	 * @param m se e movel true se nao false
	 * @param ms massa do objeto
	 */
	public CircleCollider(double r, double e, Vector2 pos, String t,boolean m,double ms){
		super(pos,t,m,ms);
		radius = r;
		elasticity = e;
	}
	
	/**
	 * Construtor baseado em posicao e raio
	 * @param r raio
	 * @param x posicao x
	 * @param y posicao y
	 */
	public CircleCollider(double r, double x, double y){
		radius = r;
		this.position.x = x;
		this.position.y = y; 
	}
	
	/**
	 * Metodo para obter a massa do collider
	 * @return retorna a massa
	 */
	public double getMass(){
		return mass;
	}
	/**
	 * Metodo para defenir a massa do collider
	 * @param m massa
	 */
	public void setMass(double m){
		this.mass = m;
	}
	
	@Override
	public Rectangulo getBoundingBox() {  
		Rectangulo res = new Rectangulo(this.position.x - radius,this.position.y - radius,radius * 2);
		return res;
	}

	/**
	 * Metodo para obter o raio
	 * @return devolve o raio
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * Metodo para defenir o raio
	 * @param radius raio
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * Metodo para obter a elasticidade
	 * @return devolve elasticidade
	 */
	public double getElasticity() {
		return elasticity;
	}
	/**
	 * Metodo para defenir a eslasticidade
	 * @param elasticity elasticidade
	 */
	public void setElasticity(double elasticity) {
		this.elasticity = elasticity;
	}

}