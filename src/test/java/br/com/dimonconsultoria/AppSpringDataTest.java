package br.com.dimonconsultoria;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.dimonconsultoria.dao.InterfaceSpringDataUser;
import br.com.dimonconsultoria.dao.InterfaceTelefone;
import br.com.dimonconsultoria.model.Telefone;
import br.com.dimonconsultoria.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone; 

	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("juvenal@java.com.br");
		usuarioSpringData.setIdade(38);
		usuarioSpringData.setLogin("teste 456");
		usuarioSpringData.setSenha("456");
		usuarioSpringData.setNome("Juvabfasd");
		interfaceSpringDataUser.save(usuarioSpringData);
	}

	@Test
	public void testeConsulta() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getId());
	}

	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData u : lista) {
			System.out.println(u.getNome());
			System.out.println(u.getLogin());
			System.out.println(u.getIdade());
			System.out.println(u.getSenha());
			System.out.println(u.getEmail());
			System.out.println(u.getId());
			System.out.println("-------------");
		}
	}

	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		UsuarioSpringData user = usuarioSpringData.get();
		user.setNome("Novo nome");
		interfaceSpringDataUser.save(user);
	}

	@Test
	public void testeDelete() {
		interfaceSpringDataUser.deleteById(1L);
	}

	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> lista = interfaceSpringDataUser.buscaPorNome("Jucemar");
		for (UsuarioSpringData u : lista) {
			System.out.println(u.getNome());
			System.out.println(u.getLogin());
			System.out.println(u.getIdade());
			System.out.println(u.getSenha());
			System.out.println(u.getEmail());
			System.out.println(u.getId());
			System.out.println("-------------");
		}
	}

	@Test
	public void testeConsultaNomeComParam() {
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Juliano 3");
		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getIdade());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getId());
		System.out.println("-------------");
	}
	
	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Juliano Alex");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("jucemar@teste.com", "Juliano");
	}
	
	@Test 
	public void testeInsertTelefone() {
		Optional<UsuarioSpringData>  usuario = interfaceSpringDataUser.findById(6L);
		Telefone telefone=new Telefone();
		telefone.setTipo("CELULAR");
		telefone.setNumero("4833486435");
		telefone.setUsuarioSpringData(usuario.get());
		interfaceTelefone.save(telefone);
	}

}
