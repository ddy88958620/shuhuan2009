package com.happinesstree.oauth2.exceptions;

/**
 * 
 * @Title: SerializationException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:05:49
 * @author shuhuan@qiyi.com
 */
@SuppressWarnings("serial")
public class SerializationException extends RuntimeException {

  public SerializationException() {
  }

  public SerializationException(String message) {
    super(message);
  }

  public SerializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public SerializationException(Throwable cause) {
    super(cause);
  }
}
