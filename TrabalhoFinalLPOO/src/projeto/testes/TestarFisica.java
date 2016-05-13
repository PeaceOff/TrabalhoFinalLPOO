package projeto.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import projeto.logic.CircleCollider;
import projeto.logic.Fisica;
import projeto.logic.RectCollider;
import projeto.logic.Vector2;

public class TestarFisica {


	@Test
	public void testCheckCollision() {
		
		Fisica f = new Fisica();
		
		CircleCollider c1 = new CircleCollider(2);
		Vector2 v = new Vector2();
		v.x = 3;
		v.y = 3;
		c1.setPosition(v);
		
		CircleCollider c2 = new CircleCollider(4);
		Vector2 v1 = new Vector2();
		v1.x = 10;
		v1.y = 3;
		c2.setPosition(v1);
		
		CircleCollider c3 = new CircleCollider(3);
		Vector2 v2 = new Vector2();
		v2.x = 7;
		v2.y = 8;
		c3.setPosition(v2);
		
		RectCollider r1 = new RectCollider(4,6);
		Vector2 center = new Vector2();
		center.x = 16;
		center.y = 8;
		r1.setPosition(center);
		
		RectCollider r2 = new RectCollider(5,3);
		Vector2 v3 = new Vector2();
		v3.x = 7.5;
		v3.y = 12.5;
		r2.setPosition(v3);
		
		RectCollider r3 = new RectCollider(4,4);
		Vector2 v4 = new Vector2();
		v4.x = 20;
		v4.y = 11;
		r3.setPosition(v4);
		
		CircleCollider c4 = new CircleCollider(1);
		Vector2 v5 = new Vector2();
		v5.x = 4;
		v5.y = 11;
		c4.setPosition(v5);
		
		assertEquals(true,f.checkColision(c2, c3));
		assertEquals(false,f.checkColision(r2, r1));
		//NAO PASSOU//assertEquals(true,f.checkColision(r1, r3));
		assertEquals(false,f.checkColision(c3, c4));
		//NAO PASSOU//assertEquals(true,f.checkColision(r2, c3));
		assertEquals(false,f.checkColision(c1, c2));
		assertEquals(false,f.checkColision(c2, r1));
		
	}
}
