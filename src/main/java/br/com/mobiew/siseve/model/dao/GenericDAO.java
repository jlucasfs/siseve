package br.com.mobiew.siseve.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDAO<T, ID extends Serializable> {

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
     * Consulta todos os registros da entidade ordenados
     * 
     * @param propertyName Nome do campo a ser ordenado
     * @return Lista com os registros da entidade
     */
    public List<T> findAllOrdered( final String propertyName, final Boolean asc );

    /**
     * Consulta a partir de um objeto instanciado da entidade
     * 
     * @param propertyName Nome do campo a ser filtrado
     * @param value Valor do campo a ser filtrado
     * @return Lista com os registros da entidade
     */
    List<T> findAllByProperty( String propertyName, Object value );

    /**
     * Insere um registro da entidade
     * 
     * @param entity Objeto com as informações a serem persistidas.
     * @return A chave do objeto persistido.
     */
    T save( T entity );
    
    T persist( T entity );
    
    T merge( T merge );
    
    /**
     * Inicializa um objeto que está mapeado no Hibernate como Lazy
     * @param proxy
     */
    public void initialize( Object proxy );

    /**
     * Exclui um registro da entidade e tornar o objeto transiente
     * 
     * @param entity O objeto da entidade com o registro a ser removido.
     */
    void delete( T entity );
    
    public List<T> findByCriteria( final DetachedCriteria detachedCriteriaParam );
}
