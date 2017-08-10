/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extention;

public class BaseResponse {
    
    private String status;
    private Object err_body;
    private Object msg_body;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the err_body
     */
    public Object getErr_body() {
        return err_body;
    }

    /**
     * @param err_body the err_body to set
     */
    public void setErr_body(Object err_body) {
        this.err_body = err_body;
    }

    /**
     * @return the msg_body
     */
    public Object getMsg_body() {
        return msg_body;
    }

    /**
     * @param msg_body the msg_body to set
     */
    public void setMsg_body(Object msg_body) {
        this.msg_body = msg_body;
    }
    
    
}
