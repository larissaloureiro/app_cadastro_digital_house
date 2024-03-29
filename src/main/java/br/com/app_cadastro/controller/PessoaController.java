package br.com.app_cadastro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app_cadastro.domain.vo.v1.PessoaVO;
import br.com.app_cadastro.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Pessoa Endpoint")
@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {
	
	@Autowired
	PessoaService service;
	
//	@CrossOrigin("localhost:8080") //permitido o acesso
	@RequestMapping(method=RequestMethod.GET, produces={"application/json","application/xml"})
	@Operation(summary="Listar todas as Pessoas")
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<CollectionModel<PessoaVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<PessoaVO> pessoasVO = service.buscarTodos(pageable);
		pessoasVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(pessoasVO));
	}
	
//	@CrossOrigin({"localhost:8080", "http://www.fgateste.com.br"}) //permitido o acesso
	// @RequestMapping(method=RequestMethod.GET, value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE) // Mesma função da linha seguinte
	@GetMapping(value="/{id}", produces={"application/json","application/xml"})
	@Operation(summary="Buscar Pessoa pelo Id")
	@ResponseStatus(value=HttpStatus.OK)
	public PessoaVO findById(@PathVariable("id") Long id) {
		PessoaVO pessoaVO = service.buscarPorId(id);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return pessoaVO;
	}
	
	@PostMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@Operation(summary="Criar nova Pessoa")
	@ResponseStatus(value=HttpStatus.CREATED)
	public PessoaVO create(@Valid @RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO = service.inserir(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@PutMapping(consumes = {"application/json","application/xml"}, produces={"application/json","application/xml"})
	@Operation(summary="Atualizar Pessoa")
	@ResponseStatus(value=HttpStatus.OK)
	public PessoaVO update(@Valid @RequestBody PessoaVO pessoa) {
		PessoaVO pessoaVO = service.atualizar(pessoa);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).findById(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@DeleteMapping(value="/{id}", produces={"application/json","application/xml"})
	@Operation(summary="Apagar Pessoa")
	@ResponseStatus(value=HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@CrossOrigin("localhost:8080")
    @Operation(summary="Listar pessoas por nome")
    @GetMapping(value="/buscarPorNome/{nome}",
                           produces = {"application/json", "application/xml"})
    public ResponseEntity<CollectionModel<PessoaVO>> findPersonByName(
                           @PathVariable("nome") String nome,
                           @RequestParam(value="page", defaultValue="0") int page,
                           @RequestParam(value="limit", defaultValue="10") int limit,
                           @RequestParam(value="direction", defaultValue="asc") String direction){
                var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC:Direction.ASC;
                Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDirection, "nome"));
                Page<PessoaVO> pessoasVO = service.findByNome(nome, pageable);
                pessoasVO
                           .stream()
                           .forEach(p -> p.add(linkTo(methodOn(PessoaController.class)
                                                   .findById(p.getKey()))
                                                   .withSelfRel()
                                                   )
                           );
                return ResponseEntity.ok(CollectionModel.of(pessoasVO));
    }
}
