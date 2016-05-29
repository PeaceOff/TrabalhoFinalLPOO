package projeto.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import projeto.logic.CircleCollider;
import projeto.logic.Collider;
import projeto.logic.Estatistica;
import projeto.logic.Fisica;
import projeto.logic.Input;
import projeto.logic.RectCollider;
import projeto.logic.SoccerGame;
import projeto.logic.Vector2;
import projeto.logic.iEstatisticaAlert;

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
		
		RectCollider r1 = new RectCollider(6,4);
		Vector2 center = new Vector2();
		center.x = 16;
		center.y = 8;
		r1.setPosition(center);
		
		RectCollider r2 = new RectCollider(3,5);
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
		
		CircleCollider c5 = new CircleCollider(1);
		Vector2 v6 = new Vector2();
		v6.x = 19.5;
		v6.y = 10.5;
		c5.setTrigger(true);
		c5.setPosition(v6);
		
		assertEquals(true,f.checkColision(c2, c3));
		assertEquals(false,f.checkColision(r2, r1));
		assertEquals(true,f.checkColision(r1, r3));
		assertEquals(false,f.checkColision(c3, c4));
		assertEquals(true,f.checkColision(r2, c3));
		assertEquals(false,f.checkColision(c1, c2));
		assertEquals(false,f.checkColision(c2, r1));
		assertEquals(true,f.checkColision(c5, r3));
		
		f.addObject(r1);
		f.addObject(r2);
		f.addObject(r3);
		f.addObject(c1);
		f.addObject(c2);
		f.addObject(c3);
		f.addObject(c5);
		f.removeObject(c1);
		f.update(1);
		Vector2 pos = new Vector2(16.0,8.0);
		
		assertEquals(true,(pos.x == r1.getPosition().x));
		assertEquals(true,(pos.y == r1.getPosition().y));
	}
	
	@Test
	public void testDealWithCollision() {
		
		Collider c1 = new CircleCollider(1.1);
		Vector2 p1 = new Vector2(5,10);
		Vector2 v1 = new Vector2(5,0);
		c1.setPosition(p1);
		c1.setVelocity(v1);
		c1.setMovable(true);
		c1.setTrigger(true);
		c1.setDrag(new Vector2(0,0));
		c1.setVelCap(new Vector2(10000,10000));
		
		Collider c2 = new CircleCollider(1);
		Vector2 p2 = new Vector2(7,10);
		Vector2 v2 = new Vector2(-5,0);
		c2.setPosition(p2);
		c2.setVelocity(v2);
		c2.setMovable(true);
		c2.setTrigger(true);
		c2.setDrag(new Vector2(0,0));
		c2.setVelCap(new Vector2(10000,10000));
		
		Fisica f = new Fisica();
		f.addObject(c1);
		f.addObject(c2);
		
		assertEquals(true,f.checkColision(c1,c2));
		f.update(0.05f);
		f.dealWithCollision(c1, c2);
		assertEquals(false,f.checkColision(c1,c2));
	}
	
	@Test
	public void testCircleAndRect_One() {//Em baixo
		
		Fisica f = new Fisica();
		
		Collider r = new RectCollider(5,2);
		r.setMovable(false);
		r.setTrigger(false);
		r.setPosition(new Vector2(10,10));
		
		Collider c = new CircleCollider(1.1);
		c.setMovable(true);
		c.setVelCap(new Vector2(10000,10000));
		c.setDrag(new Vector2());
		c.setTrigger(false);
		c.setVelocity(new Vector2(0,-1));
		c.setPosition(new Vector2(11,12));
		
		f.addObject(c);
		f.addObject(r);
		
		assertEquals(true,f.checkColision(c, r));
		f.dealWithCollision(c,r);
		assertEquals(false,f.checkColision(c,r));
		
	}
	
	@Test
	public void testCircleAndRect_Two() {//Em cima
		
		Fisica f = new Fisica();
		
		Collider r = new RectCollider(5,2);
		r.setMovable(false);
		r.setTrigger(false);
		r.setPosition(new Vector2(10,10));
		
		Collider c = new CircleCollider(1.1);
		c.setMovable(true);
		c.setVelCap(new Vector2(10000,10000));
		c.setDrag(new Vector2());
		c.setTrigger(false);
		c.setVelocity(new Vector2(0,1));
		c.setPosition(new Vector2(11,9));
		
		f.addObject(c);
		f.addObject(r);
		
		assertEquals(true,f.checkColision(c, r));
		f.dealWithCollision(r,c);
		assertEquals(false,f.checkColision(c,r));
		
	}
	
	@Test
	public void testCircleAndRect_Three() {//Esquerda
		
		Fisica f = new Fisica();
		
		Collider r = new RectCollider(2,5);
		r.setMovable(false);
		r.setTrigger(false);
		r.setPosition(new Vector2(10,10));
		
		Collider c = new CircleCollider(1.1);
		c.setMovable(true);
		c.setVelCap(new Vector2(10000,10000));
		c.setDrag(new Vector2());
		c.setTrigger(false);
		c.setVelocity(new Vector2(1,0));
		c.setPosition(new Vector2(9,11));
		
		f.addObject(c);
		f.addObject(r);
		
		assertEquals(true,f.checkColision(c, r));
		f.dealWithCollision(c,r);
		assertEquals(false,f.checkColision(c,r));
		
	}
	
	@Test
	public void testCircleAndRect_Four() {//Direita

		Fisica f = new Fisica();
		
		Collider r = new RectCollider(2,5);
		r.setMovable(false);
		r.setTrigger(false);
		r.setPosition(new Vector2(10,10));
		
		Collider c = new CircleCollider(1.1);
		c.setMovable(true);
		c.setVelCap(new Vector2(10000,10000));
		c.setDrag(new Vector2());
		c.setTrigger(false);
		c.setVelocity(new Vector2(-1,0));
		c.setPosition(new Vector2(12,11));
		
		f.addObject(c);
		f.addObject(r);
		
		assertEquals(true,f.checkColision(c, r));
		f.dealWithCollision(r,c);
		assertEquals(false,f.checkColision(c,r));
	}
}
