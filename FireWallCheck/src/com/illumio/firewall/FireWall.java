/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.illumio.firewall;

/**
 *
 * @author pavanrao
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FireWall {

    private static final ArrayList<Rule> ruleList = new ArrayList<>();
    private final String filename;
    private static final Logger LOG = Logger.getLogger(FireWall.class.getName());
    
    public FireWall(String filename) {
        this.filename = filename;
    }
    
    /**
     * 
     * @param direction
     * @param protocol
     * @param port
     * @param ipAddress
     * @return 
     */
    public static boolean accept_packet(String direction, String protocol, int port, String ipAddress) {
        Rule incomingPacket = new Rule();
        incomingPacket.setDirection(direction);
        incomingPacket.setProtocol(protocol);
        incomingPacket.setPort(Integer.toString(port));
        incomingPacket.setIp(ipAddress);
        if (ruleList.stream().anyMatch((rule) -> (rule.equals(incomingPacket)))) {
            LOG.log(Level.FINE, "The packet with {0} {1} {2} and {3} as specificaltions is allowed", new Object[]{incomingPacket.getDirection(), incomingPacket.getProtocol(), incomingPacket.getPort(), incomingPacket.getIp()});
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param fw 
     */
    public void compile_rule_list(FireWall fw) {
        BufferedReader bufferedReader = null;
        String line = "";
        String delimiter = ",";
        try {

            bufferedReader = new BufferedReader(new FileReader(fw.filename));
            while ((line = bufferedReader.readLine()) != null) {
                
                String[] inputRule = line.split(delimiter);

                Rule rule = new Rule();
                rule.setDirection(inputRule[0]);
                rule.setProtocol(inputRule[1]);
                rule.setPort(inputRule[2]);
                rule.setIp(inputRule[3]);
                LOG.log(Level.FINE, "Rule created with {0} {1} {2} and {3} as specificaltions", new Object[]{rule.getDirection(), rule.getProtocol(), rule.getPort(), rule.getIp()});
                ruleList.add(rule);
                
            }
            
        } 
        catch (FileNotFoundException e) {
            LOG.log(Level.SEVERE, "Given file is not found!", e);
            e.printStackTrace();
        } 
        catch (IOException e) {
            LOG.log(Level.SEVERE, "An IOException has occured!", e);
            e.printStackTrace();
        } 
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOG.log(Level.WARNING, "Failed to close the file reader!", e);
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String csvFile = "rules.csv";
        FireWall fw = new FireWall(csvFile);
        fw.compile_rule_list(fw);
 
        //satisfies rule 1
        System.out.println(accept_packet("inbound", "tcp", 80, "192.168.1.2"));
        
        //satisfies rule 2
        System.out.println(accept_packet("inbound", "udp", 53, "192.168.2.1"));
        
        //satisfies rule2
        System.out.println(accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
        
        //doesn't satisfy any rule
        System.out.println(accept_packet("inbound", "tcp", 81, "192.168.1.2"));
        
        //doesn't satisfy any rule
        System.out.println(accept_packet("inbound", "udp", 24, "52.12.48.92"));
        
        //satisfies rule 3
        System.out.println(accept_packet("inbound", "udp", 53, "192.168.1.255"));
        
        //satisfies rule 5
        System.out.println(accept_packet("outbound", "tcp", 199, "199.34.220.255"));
        
        //doesn't satisfy any rule
        System.out.println(accept_packet("outbound", "tcp", 235, "1.5.255.255"));
        
        //satisfies rule 4
        System.out.println(accept_packet("outbound", "udp", 1500, "52.12.48.92"));
    }
    
}