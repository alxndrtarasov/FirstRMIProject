/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
import java.util.*;
import java.io.*;
import javax.naming.*;

import ejb.entitybeanexample.EntityBeanHomeExampleI;
import ejb.entitybeanexample.EntityBeanExampleI;

public class EntityBeanClient {

    public static void main(String[] args) throws Exception{
        (new EntityBeanClient()).Go();
    }
    
    public void Go() throws Exception {
        Properties props = new Properties();
        
        //props.put(Context.PROVIDER_URL,"iiop://127.0.0.1:3700");
        //props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        
        //InitialContext ctx = new InitialContext(props);
        
        InitialContext ctx = new InitialContext();
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+ctx.lookup("jdbc/OracleSample"));
        ctx.rebind("jdbc/OracleSample__pm", ctx.lookup("jdbc/OracleSample"));
        
        EntityBeanHomeExampleI beanhome = (EntityBeanHomeExampleI) ctx.lookup("ejb/entitybeanexample");
        
        System.out.println(beanhome);
        
        EntityBeanExampleI bean = beanhome.findByPrimaryKey(2);
        System.out.println(bean.getName()+" "+bean.getId());
        
        bean = beanhome.create(new Integer(5), "ganja");
        //bean.remove();
        
        Collection beans = beanhome.findByNameLike("arabica");
        Iterator i = beans.iterator();
        while(i.hasNext()){
            bean=(EntityBeanExampleI)i.next();
            System.out.println(bean.getId()+" "+bean.getName());
        }
        
    }
}
