package server;

import java.util.List;

import br.com.guiainvestimento.domain.Tesouro;
import br.com.guiainvestimento.domain.TesouroService;
import junit.framework.TestCase;

public class TesouroTest extends TestCase {
	private TesouroService tesouroService = new TesouroService();
	public void testListaTesouros() {
		List<Tesouro> tesouros = tesouroService.getTesouros();
		assertNotNull(tesouros);
		assertTrue(tesouros.size() > 0);
		Tesouro lft = tesouroService.findByName("LFT 010321").get(0);
		assertEquals("LFT 010321", lft.getNome());
	}
	
	public void testSalvarDeletarTesouro() {
		Tesouro tesouro = new Tesouro();
		tesouro.setNome("NTNB");
		tesouro.setTipo("NTNB");
		tesouro.setValor(1400.35);
		tesouro.setData("20180101");
		tesouroService.save(tesouro);
		Long id = tesouro.getId();
		assertNotNull(id);
		
		tesouro = tesouroService.getTesouro(id);
		assertEquals("NTNB", tesouro.getNome());
		assertEquals("NTNB", tesouro.getTipo());
		assertEquals(1400.35, tesouro.getValor());
		assertEquals("20180101", tesouro.getData());
		
		tesouro.setNome("NTNB 2018");
		tesouroService.save(tesouro);
		tesouro = tesouroService.getTesouro(id);
		assertEquals("NTNB 2018", tesouro.getNome());
		
		tesouro = tesouroService.getTesouro(id);
		assertNull(tesouro);
	}
}
