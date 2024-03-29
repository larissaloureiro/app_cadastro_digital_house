package br.com.app_cadastro.adapter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.app_cadastro.domain.entity.Pessoa;
import br.com.app_cadastro.domain.vo.v1.PessoaVO;

public class DozerConverterTest {
	// MOCK = Criar um objeto com a informação que vai ser testada
	
	private Pessoa mockEntity(Long numero) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(0L);
		pessoa.setNome("Nome Teste" + numero);
		pessoa.setSobrenome("Sobrenome Teste" + numero);
		pessoa.setNomeSocial("NomeSocial Teste" + numero);
		pessoa.setEmail("Email Teste" + numero);
		
		return pessoa;
	}
	
	private List<Pessoa> mockEntityList() {
		List<Pessoa> pessoas = new ArrayList<>();
		for(Long i = 0L; i < 5; i++) {
			pessoas.add(mockEntity(i));
		}
		
		return pessoas;
	}
	
	private PessoaVO mockEntityVO(Long numero) {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setKey(0L);
		pessoa.setNome("Nome Teste" + numero);
		pessoa.setSobrenome("Sobrenome Teste" + numero);
		pessoa.setNomeSocial("NomeSocial Teste" + numero);
		pessoa.setEmail("Email Teste" + numero);
		
		return pessoa;
	}
	
	private List<PessoaVO> mockEntityVOList() {
		List<PessoaVO> pessoas = new ArrayList<>();
		for(Long i = 0L; i < 5; i++) {
			pessoas.add(mockEntityVO(i));
		}
		
		return pessoas;
	}
	
	// CASOS DE TESTE
	@Test
	public void parseEntityToVOTest() {
		PessoaVO output = DozerConverter.parseObject(mockEntity(0L), PessoaVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("Nome Teste0", output.getNome());
		Assert.assertEquals("Sobrenome Teste0", output.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", output.getNomeSocial());
		Assert.assertEquals("Email Teste0", output.getEmail());
	}
	
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PessoaVO> outputList = DozerConverter.parseListObject(mockEntityList(), PessoaVO.class);
		PessoaVO outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("Nome Teste0", outputZero.getNome());
		Assert.assertEquals("Sobrenome Teste0", outputZero.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", outputZero.getNomeSocial());
		Assert.assertEquals("Email Teste0", outputZero.getEmail());	
	}
	
	@Test
	public void parseVOtoEntityTest() {
		Pessoa output = DozerConverter.parseObject(mockEntityVO(0L), Pessoa.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Nome Teste0", output.getNome());
		Assert.assertEquals("Sobrenome Teste0", output.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", output.getNomeSocial());
		Assert.assertEquals("Email Teste0", output.getEmail());
	}
	
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Pessoa> outputList = DozerConverter.parseListObject(mockEntityVOList(), Pessoa.class);
		Pessoa outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("Nome Teste0", outputZero.getNome());
		Assert.assertEquals("Sobrenome Teste0", outputZero.getSobrenome());
		Assert.assertEquals("NomeSocial Teste0", outputZero.getNomeSocial());
		Assert.assertEquals("Email Teste0", outputZero.getEmail());	
	}
	
	

}
