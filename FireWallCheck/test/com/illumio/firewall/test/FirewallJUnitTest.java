package com.illumio.firewall.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.illumio.firewall.FireWall;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavanrao
 */
public class FirewallJUnitTest {
    
    public FirewallJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FireWall fw = new FireWall("rules.csv");
        fw.compile_rule_list(fw);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         
        //satisfies rule 1
        assertEquals(true, FireWall.accept_packet("inbound", "tcp",80,"192.168.1.2"));
        
        //satisfies rule 2
        assertEquals(true, FireWall.accept_packet("inbound", "udp",53,"192.168.2.1"));
        
        //satisfies rule2
        assertEquals(true, FireWall.accept_packet("outbound", "tcp",10234,"192.168.10.11"));
        
        //doesn't satisfy any rule
        assertEquals(false, FireWall.accept_packet("inbound", "tcp",81,"192.168.1.2"));
        
        //doesn't satisfy any rule
        assertEquals(false, FireWall.accept_packet("inbound", "udp",24,"52.12.48.92"));
        
        //satisfies rule 3
        assertEquals(true, FireWall.accept_packet("inbound", "udp",53,"192.168.1.255"));
        
        //satisfies rule 5
        assertEquals(true, FireWall.accept_packet("outbound", "tcp",199,"199.34.220.255")); 
        
        //doesn't satisfy any rule
        assertEquals(false, FireWall.accept_packet("outbound", "tcp",235,"1.5.255.255"));
        
        //satisfies rule 4
        assertEquals(true, FireWall.accept_packet("outbound", "udp",1500,"52.12.48.92"));
     }
}
