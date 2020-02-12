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
public class Validator {
    
        /**
     * 
     * @param ipOfTheRule - IP Address of the rule in the rule list
     * @param ipOfThePacket - IP Address of the packet to be verified
     * @return 
     */
    public static boolean validateIP(String ipOfTheRule, String ipOfThePacket) {
        
        /**
         * If the IP address of the rule contains a range:
         * Example: 192.168.1.1-192.168.2.5
         */
        if(ipOfTheRule.contains("-")) {
            Double inputIpAddress = Util.convert_IP_to_Int(ipOfThePacket);
            String[] ipAddresses = ipOfTheRule.split("-");
            Double minIpAddress = Util.convert_IP_to_Int(ipAddresses[0]);
            Double maxIpAddress = Util.convert_IP_to_Int(ipAddresses[1]);
            return inputIpAddress <= maxIpAddress && inputIpAddress >= minIpAddress;
        }
        /**
         * If the IP address of the rule is a numeral
         * Example: 52.12.48.92
         */
        else  {
            if(ipOfTheRule.equals(ipOfThePacket))
                return true;
        }
        return false;
        
    }
    
        /**
     * 
     * @param portOfTheRule - port of the rule in the rule list
     * @param portOfThePacket - port of the packet to be verified
     * @return 
     */
    public static boolean validatePort(String portOfTheRule, String portOfThePacket) {
        
        /**
         * If the port of the rule contains a range:
         * Example: 80-85
         */
        if(portOfTheRule.contains("-")){
            String[] portRange = portOfTheRule.split("-");
            int minPort = Integer.parseInt(portRange[0]);
            int maxPort = Integer.parseInt(portRange[1]);
            return Integer.parseInt(portOfThePacket) <= maxPort && Integer.parseInt(portOfThePacket) >= minPort;
        }
        /**
         * If the port of the rule is a numeral
         * Example: 81
         */
        else {
            if(portOfTheRule.equals(portOfThePacket))
                return true;
        }
        return false;
        
    }
    
}