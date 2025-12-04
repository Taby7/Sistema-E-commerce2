package com.ecommerce.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.br.entity.Role;
import com.ecommerce.br.entity.Usuario;
import com.ecommerce.br.service.RoleService;
import com.ecommerce.br.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

@Autowired
protected UsuarioService usuarioService;

@Autowired
protected RoleService roleService;

@GetMapping("/form")
public String home(Model model) {
model.addAttribute("usuario", new Usuario());
model.addAttribute("roles", roleService.list());
return "cadastrarUsuario";
}

@PostMapping("/save")
public String saveUsuario(@ModelAttribute Usuario usuario, Model model, @RequestParam(required=false) List<Long> roles) {
if (usuario.getUsername() != null && usuarioService.existsByUsername(usuario.getUsername())) {
model.addAttribute("mensagemErro", "Usuário com username " + usuario.getUsername() + " já existe");
model.addAttribute("roles", roleService.list());
return "cadastrarUsuario";
}
if (roles != null) {
List<Role> rolesSelecionadas = roleService.list().stream().filter(r -> roles.contains(r.getId())).toList();
usuario.setRoles(rolesSelecionadas);
}
usuarioService.save(usuario);
model.addAttribute("mensagemSucesso", "Usuário " + usuario.getNome() + " salvo com sucesso");
return "cadastrarUsuario";
}


@GetMapping("/delete/{id}")
public String deleteUsuario(@PathVariable Long id, Model model) {
    try {
        Usuario usuario = usuarioService.findById(id);
        usuarioService.deleteById(id);
        model.addAttribute("usuarios", usuarioService.list());
        model.addAttribute("mensagemSucesso", "Usuario " + usuario.getNome() + " removida com sucesso");
        return "listarUsuario";
    } catch (RuntimeException e) {
        model.addAttribute("mensagemErro", e.getMessage());
        model.addAttribute("usuarios", usuarioService.list());
        return "listarUsuario";
    }
}


@GetMapping("/list")
public String listarUsuario(Model model) {
model.addAttribute("usuarios", usuarioService.list());
return "listarUsuario";
}

@GetMapping("/edit/{id}")
public String editRole(@PathVariable Long id, Model model) {
Usuario usuario = usuarioService.findById(id);
model.addAttribute("usuario", usuario);
model.addAttribute("roles", roleService.list());
return "editarUsuario";
}

@PostMapping("/update")
public String updateUsuario(@ModelAttribute Usuario usuarioAtualizado, Model model, @RequestParam(required=false) List<Long> roles) {
    
    Usuario usuarioOriginal = usuarioService.findById(usuarioAtualizado.getId()); 

    usuarioOriginal.setNome(usuarioAtualizado.getNome());
    usuarioOriginal.setUsername(usuarioAtualizado.getUsername());
    usuarioOriginal.setEmail(usuarioAtualizado.getEmail());
    usuarioOriginal.setLogin(usuarioAtualizado.getLogin());
    
   
    if (roles != null) {
        List<Role> rolesSelecionadas = roleService.list().stream().filter(r -> roles.contains(r.getId())).toList();
        usuarioOriginal.setRoles(rolesSelecionadas);
    } 

   
    usuarioService.save(usuarioOriginal);
    
    model.addAttribute("mensagemSucesso", "Usuário " + usuarioOriginal.getNome() + " editado com sucesso");
    model.addAttribute("usuarios", usuarioService.list());
   
    return "listarUsuario";
}

}
