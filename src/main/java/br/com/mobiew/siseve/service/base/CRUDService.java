package br.com.mobiew.siseve.service.base;

import java.io.Serializable;
import java.util.List;

public interface CRUDService<T, ID extends Serializable> {

    /**
     * Método de consulta passando somente a chave primária da entidade
     * 
     * @param id Id da entidade
     * @return A entidade
     */
    T findById( ID id );

    /**
     * Consulta todos os registros da entidade
     * 
     * @return Lista com os registros da entidade
     */
    List<T> findAll();

    /**
     * Insere um registro da entidade
     * 
     * @param entity Objeto com as informações a serem persistidas.
     * @return A chave do objeto persistido.
     */
    T save( T entity );

    /**
     * Exclui um registro da entidade e tornar o objeto transiente
     * 
     * @param entity O objeto da entidade com o registro a ser removido.
     */
    void delete( T entity );
    
    T persist( T entity );
    
    T merge( T merge );
}
