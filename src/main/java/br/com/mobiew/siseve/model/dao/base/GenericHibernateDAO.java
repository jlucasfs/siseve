package br.com.mobiew.siseve.model.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.mobiew.siseve.model.dao.GenericDAO;

public class GenericHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

    private Class<T> type;
    
    @SuppressWarnings( "unchecked" )
    public GenericHibernateDAO() {
        this.type = (Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
    }

    public Class<T> getType() {
        return this.type;
    }

    /**
     * hibernate
     * 
     * @param proxy proxy
     */
    public void initialize( Object proxy ) {
        Hibernate.initialize( proxy );
    }

    /**
     * Insere um registro da entidade
     * 
     * @param entity Objeto com as informacoes a serem persistidas.
     * @return A chave do objeto persistido.
     */
    public T save( final T entity ) {
    	getHibernateTemplate().flush();
        getHibernateTemplate().saveOrUpdate( entity );
        return entity;
    }
    
    /**
     * Persiste a entidade
     * 
     * @param entity Objeto com as informacoes a serem persistidas.
     * @return A chave do objeto persistido.
     */
    public T persist( final T entity ) {
        getHibernateTemplate().persist( entity );
        return entity;
    }
    
    /**
     * Altera a entidade
     * 
     * @param entity Objeto com as informacoes a serem persistidas.
     * @return A chave do objeto alterado.
     */
    public T merge( final T entity ) {
        getHibernateTemplate().merge( entity );
        return entity;
    }
    
    /**
     * Metodo de consulta passando somente a chave primaria da entidade
     * 
     * @param id Id da entidade
     * @return A entidade
     */
    public T findById( final ID id ) {
        return getHibernateTemplate().get( type, id );
    }

    /**
     * Consulta todos os registros da entidade
     * 
     * @return Lista com os registros da entidade
     */
    public List<T> findAll() {
        return getHibernateTemplate().loadAll( this.type );
    }
    
    /**
     * Consulta todos os registros da entidade ordenados
     * 
     * @param propertyName Nome do campo a ser ordenado
     * @return Lista com os registros da entidade
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findAllOrdered( final String propertyName, final Boolean asc ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( this.type ).addOrder( asc ? Order.asc(propertyName) : Order.desc(propertyName) );
        criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        return getHibernateTemplate().findByCriteria( criteria );
    }

    /**
     * Consulta a partir de um objeto instanciado da entidade
     * 
     * @param propertyName Nome do campo a ser filtrado
     * @param value Valor do campo a ser filtrado
     * @return Lista com os registros da entidade
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findAllByProperty( final String propertyName, final Object value ) {
        DetachedCriteria criteria = DetachedCriteria.forClass( this.type ).add( Restrictions.eq( propertyName, value ) );
        return getHibernateTemplate().findByCriteria( criteria );
    }

    /**
     * Busca por registros utilizando um objeto preenchido da entidade.
     * 
     * @param entity Objeto da entidade preenchido com os valores a serem considerados na busca
     * @return Lista com os registros a serem buscados de acordo com o objeto preenchido
     */
    public List<T> findByExample( final T entity ) {
        return findByExample( entity, 0, 1 );
    }

    /**
     * Busca por registros utilizando um objeto preenchido da entidade para paginacao.
     * 
     * @param entity Objeto da entidade preenchido com os valores a serem considerados na busca.
     * @param firstResult O indice inicial da consulta.
     * @param maxResults O valor maximo de registros retornados.
     * @return Lista com os registros a serem buscados de acordo com o objeto preenchido
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findByExample( final T entity, final int firstResult, final int maxResults ) {
        return getHibernateTemplate().findByExample( entity, firstResult, maxResults );
    }

    /**
     * Exclui um registro da entidade e tornar o objeto transiente
     * 
     * @param entity O objeto da entidade com o registro a ser removido.
     */
    public void delete( final T entity ) {
        getHibernateTemplate().delete( entity );
    }
    
    /**
     * Returns the list by the given detached criteria.
     *
     * @param detachedCriteriaParam the detached criteria.
     * @return The list.
     */
    @SuppressWarnings( "unchecked" )
    public List<T> findByCriteria( final DetachedCriteria detachedCriteriaParam ) {
        return (getHibernateTemplate().findByCriteria( detachedCriteriaParam ) );
    }
}
