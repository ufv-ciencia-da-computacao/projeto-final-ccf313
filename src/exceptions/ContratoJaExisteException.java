/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author dener
 */
public class ContratoJaExisteException extends Exception {

    @Override
    public String getMessage() {
        return "Este contrato ja existe";
    }
    
    
}
