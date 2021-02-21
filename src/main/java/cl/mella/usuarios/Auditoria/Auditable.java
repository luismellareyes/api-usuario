package cl.mella.usuarios.Auditoria;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Auditable<U> {


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date modified;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date last_login;


}
