package br.com.mobiew.siseve.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobiew.siseve.model.dao.ProfissionalDAO;
import br.com.mobiew.siseve.model.entity.Profissional;
import br.com.mobiew.siseve.model.entity.Usuario;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

    private ProfissionalDAO dao;

    @Autowired
    public ProfissionalServiceImpl( final ProfissionalDAO daoParam ) {
        this.dao = daoParam;
    }

    @Override
    public Profissional findById( Long id ) {
        if ( id == null ) {
            return null;
        }
        Profissional prof = this.dao.findById( id );
        return prof;
    }

    @Override
    public List<Profissional> findAll() {
        List<Profissional> lista = this.dao.findAll();
        Collections.sort( lista, new Comparator<Profissional>() {
            public int compare( Profissional o1Param, Profissional o2Param ) {
                return o1Param.getNome().compareTo( o2Param.getNome() );
            }
        } );
        return lista;
    }

    @Override
    @Transactional
    public Profissional save( Profissional entity ) {
        return this.dao.save( entity );
    }

    @Override
    @Transactional
    public void delete( Profissional entity ) {
        this.dao.delete( entity );
    }

    @Override
    public Profissional persist( Profissional entity ) {
        return this.dao.persist( entity );
    }

    @Override
    public Profissional merge( Profissional merge ) {
        return this.dao.merge( merge );
    }

    @Override
    public List<Profissional> findAll( String nomeParam, String cpfParam ) {
        List<Profissional> lista = this.dao.findAll( nomeParam, cpfParam );
        return lista;
    }
    
    @Override
    public Profissional findByUsuario( Usuario usuario ) {
        if ( usuario == null ) {
            return null;
        }
        return this.dao.findByUsuario( usuario );
    }
}
