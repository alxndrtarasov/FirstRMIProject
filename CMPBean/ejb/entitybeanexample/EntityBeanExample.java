/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entitybeanexample;

/**
 *
 * @author Alex
 */
import javax.ejb.*;
import java.io.Serializable;
import java.util.*;
import java.rmi.*;

public abstract class EntityBeanExample implements EntityBean {

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public void ejbLoad() {
    }

    public void ejbStore() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void setEntityContext(EntityContext ctx) {
    }

    public void unsetEntityContext() {
    }

    public void ejbRemove() throws RemoveException {
    }

    public void ejbPostCreate(Integer id, String name) throws CreateException {
    }

    public Integer ejbCreate(Integer id, String name) throws CreateException {
        setId(id);
        setName(name);
        return null;
    }
}
