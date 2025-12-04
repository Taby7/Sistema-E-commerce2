package com.ecommerce.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.br.entity.Usuario;
import com.ecommerce.br.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	protected UsuarioRepository usuarioRepository;

	public Usuario save(Usuario u) {
		return usuarioRepository.save(u);
	}

	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}

	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public boolean existsByUsername(String username) {
		return usuarioRepository.existsByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByUsername(username);
	}
}