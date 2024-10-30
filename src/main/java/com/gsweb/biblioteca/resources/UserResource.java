package com.gsweb.biblioteca.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gsweb.biblioteca.entities.User;
import com.gsweb.biblioteca.services.UserService;

@Controller
@RequestMapping(value = "/clientes")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ModelAndView listarUsuarios() {
	    List<User> clientes = service.findAll();
	    ModelAndView mv = new ModelAndView("Cliente/clientes");
	    mv.addObject("clientes", clientes);
	    return mv;
	}
	
	@GetMapping("/createCliente")
	public ModelAndView createClienteNovo() {
	    ModelAndView mv = new ModelAndView("Cliente/createCliente");
	    mv.addObject("cliente", new User());
	    return mv;
	}
	
	@PostMapping("/createCliente")
	public String createCliente(User cliente) {
		service.insert(cliente);
		return "redirect:/clientes";
	}
	
	@PostMapping("/atualizar")
	public String atualizar(User user) {
		service.update(user.getCpf(), user);
		return "redirect:/clientes";
	}
	
	@GetMapping("/editar/{cpf}")
	public ModelAndView edit(@PathVariable("cpf") String cpf) {
		ModelAndView mv = new ModelAndView("Cliente/atualizarCliente");
		User UserFind = service.findbyId(cpf);
		mv.addObject("cliente", UserFind);
		return mv;
	}
	
	@GetMapping("/excluir/{cpf}")
	public String deletar(@PathVariable("cpf") String cpf) {
		service.delete(cpf);
		return "redirect:/clientes";
	}
}
