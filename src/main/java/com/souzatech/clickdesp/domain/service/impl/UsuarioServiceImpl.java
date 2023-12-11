package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.UsuarioCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.UsuarioResponse;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.repository.UsuarioRepository;
import com.souzatech.clickdesp.domain.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    public static final String MSG_ID_NULO = "Id %d da usuario deve ser nulo";
    public static final String MSG_USUARIO_NAO_ENCONTRADO = "Não existe um cadastro de usuario com código %d";
    public static final String MSG_USUARIO_EM_USO = "Usuario de código %d não pode ser removida, pois está em uso";

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsuarioResponse> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse findById(Long id) {
        Usuario usuario = getUsuarioId(id);
        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    @Override
    public UsuarioResponse create(UsuarioCreateRequest request) {
        Usuario usuario = getUsuario(request);

        usuario = repository.save(usuario);

        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    @Override
    public UsuarioResponse update(Long id, UsuarioCreateRequest request) {
        Usuario usuario = getUsuarioId(id);
        usuario = getUsuario(request);
        usuario.setId(id);
        usuario = repository.save(usuario);

        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    @Override
    public void delete(Long id) {
        try {
           repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_USUARIO_NAO_ENCONTRADO, id));

        } catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    String.format(MSG_USUARIO_EM_USO, id));
        }

    }

    private Usuario getUsuario(UsuarioCreateRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setSobrenome(request.getSobrenome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(pe.encode(request.getSenha()));
        return usuario;
    }

    private Usuario getUsuarioId(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_USUARIO_NAO_ENCONTRADO, id));
        }
        return usuario.get();
    }
}
