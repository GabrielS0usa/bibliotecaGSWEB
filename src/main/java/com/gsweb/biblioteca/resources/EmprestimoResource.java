package com.gsweb.biblioteca.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gsweb.biblioteca.entities.Emprestimo;
import com.gsweb.biblioteca.entities.Livro;
import com.gsweb.biblioteca.entities.User;
import com.gsweb.biblioteca.services.EmprestimoService;
import com.gsweb.biblioteca.services.LivroService;
import com.gsweb.biblioteca.services.UserService;

@Controller
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {
	
	@Autowired
	private EmprestimoService service;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
    private UserService clienteService;

	
	@GetMapping
	public ModelAndView listarEmprestimos() {
	    List<Emprestimo> emprestimos = service.findAll();
	    ModelAndView mv = new ModelAndView("Emprestimo/emprestimos");
	    mv.addObject("emprestimos", emprestimos);
	    return mv;
	}
	
	@GetMapping("/createEmprestimo")
	public ModelAndView createEmprestimoNovo() {
	    ModelAndView mv = new ModelAndView("Emprestimo/createEmprestimo");
	    List<Livro> livros = livroService.findAllEmprestimos();
	    List<User> clientes = clienteService.findAll();
	    mv.addObject("usuarios", clientes);
	    mv.addObject("livros", livros);
	    mv.addObject("emprestimo", new Emprestimo());
	    return mv;
	}
	
	@PostMapping("/createEmprestimo")
	public String createEmprestimo(@RequestParam("livros") List<String> livrosIsbn,  @ModelAttribute Emprestimo emprestimo) {
	    service.insert(emprestimo, livrosIsbn);
	    return "redirect:/emprestimos";
	}
	
	
	@PostMapping("/devolucao")
	public String devolucao(Emprestimo emprestimo) {
		service.update(emprestimo.getCodEmprestimo(), emprestimo);
		return "redirect:/emprestimos";
	}
	
	@GetMapping("/devolucao/{codEmprestimo}")
	public ModelAndView edit(@PathVariable("codEmprestimo") Long codEmprestimo) {
		ModelAndView mv = new ModelAndView("Emprestimo/devolucaoEmprestimo");
		Emprestimo emprestimoFind = service.findbyId(codEmprestimo);
		mv.addObject("emprestimo", emprestimoFind);
		return mv;
	}
}
