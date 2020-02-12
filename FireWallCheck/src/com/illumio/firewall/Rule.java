/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.illumio.firewall;

import java.util.Objects;

/**
 *
 * @author pavanrao
 */
public class Rule {
    private String direction;
    private String protocol;
    private String port;
    private String ip;

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
        if (!Objects.equals(this.protocol, other.protocol)) {
            return false;
        }
        if(!Validator.validatePort(this.port, other.port)) {
            return false; 
        }
        if (!Validator.validateIP(this.ip, other.ip)) {
            return false;
        }
        return true;
    }
    
}