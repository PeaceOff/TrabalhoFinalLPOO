package projeto.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import projeto.logic.Fisica;

public class TestarFisica {

	@Test
	public void testFisicaSingleton() {
		assertEquals(Fisica.class,Fisica.getInstance().getClass());
	}

}
