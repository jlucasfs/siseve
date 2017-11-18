package br.com.mobiew.siseve.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author antonio.filho
 */
@MappedSuperclass
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class EntidadeGenerica {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Basic( optional = false )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    public EntidadeGenerica() {

    }

    public EntidadeGenerica( Long id ) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        EntidadeGenerica other = (EntidadeGenerica) obj;
        if ( id == null ) {
            if ( other.id != null ) return false;
        } else if ( !id.equals( other.id ) ) return false;
        return true;
    }

}
