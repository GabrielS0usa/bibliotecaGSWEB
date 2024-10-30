package com.gsweb.biblioteca.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gsweb.biblioteca.entities.Livro;
import com.gsweb.biblioteca.services.LivroService;

@Controller
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@GetMapping()
	public ModelAndView listarLivros() {
	    List<Livro> livros = service.findAll();
	    ModelAndView mv = new ModelAndView("Livro/livros");
	    mv.addObject("livros", livros);
	    return mv;
	}
	
	@GetMapping("/create")
	public ModelAndView createNovo() {
	    ModelAndView mv = new ModelAndView("Livro/create");
	    mv.addObject("livro", new Livro());
	    return mv;
	}
	
	@PostMapping("/create")
	public String create(Livro livro) {
		service.insert(livro);
		return "redirect:/livros";
	}
	
	@PutMapping("/atualizar")
	public String atualizar(Livro livro) {
		service.update(livro.getIsbn(), livro);
		return "redirect:/livros";
	}
	
	@GetMapping("/editar/{isbn}")
	public ModelAndView edit(@PathVariable String isbn) {
		ModelAndView mv = new ModelAndView("Livro/atualizar");
		List<Livro> livros = service.findAll();
		Livro livroFind = livros.stream().filter(livro -> isbn.equals(livro.getIsbn())).findFirst().get();
		mv.addObject("livro", livroFind);
		return mv;
	}
	
	@GetMapping("/excluir/{isbn}")
	public String deletar(@PathVariable String isbn) {
		service.delete(isbn);
		return "redirect:/livros";
	}
	
}
