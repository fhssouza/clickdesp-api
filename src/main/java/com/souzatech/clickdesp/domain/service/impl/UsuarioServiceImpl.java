package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.UsuarioDTO;
import com.souzatech.clickdesp.domain.exception.BadRequestException;
import com.souzatech.clickdesp.domain.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.domain.exception.NotFoundException;
import com.souzatech.clickdesp.domain.mapper.UsuarioMapper;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.repository.UsuarioRepository;
import com.souzatech.clickdesp.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return getUsuario(id);
    }

    @Override
    public Usuario create(UsuarioDTO dto) {
        if(Objects.nonNull(dto.getId())){
            throw new BadRequestException(
                    String.format(MSG_ID_NULO, dto.getId()));
        }
        Usuario usuario = new Usuario(
                null,
                dto.getNome(),
                dto.getSobrenome(),
                dto.getEmail(),
                pe.encode(dto.getSenha())
        );

        return repository.save(usuario);
    }

    @Override
    public Usuario update(Long id, UsuarioDTO dto) {
        getUsuario(id);
        dto.setId(id);
        return repository.save(UsuarioMapper.fromDtoEntity(dto));
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

    private Usuario getUsuario(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_USUARIO_NAO_ENCONTRADO, id));
        }
        return usuario.get();
    }
}
