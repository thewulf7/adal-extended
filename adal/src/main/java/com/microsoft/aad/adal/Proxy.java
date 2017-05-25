package com.microsoft.aad.adal;

import java.io.Serializable;

class Proxy implements Serializable {

    /**
     * Represents the proxy type.
     *
     * @since 1.5
     */
    public enum Type {
        /**
         * Represents a direct connection, or the absence of a proxy.
         */
        DIRECT,
        /**
         * Represents proxy for high level protocols such as HTTP or FTP.
         */
        HTTP,
        /**
         * Represents a SOCKS (V4 or V5) proxy.
         */
        SOCKS
    };

    private Type type;

    private String host;

    private Integer port;

    private String user;

    private String password;

    /**
     * A proxy setting that represents a <code>DIRECT</code> connection,
     * basically telling the protocol handler not to use any proxying.
     * Used, for instance, to create sockets bypassing any other global
     * proxy settings (like SOCKS):
     * <P>
     * <code>Socket s = new Socket(Proxy.NO_PROXY);</code><br>
     * <P>
     */
    public final static Proxy NO_PROXY = new Proxy();

    // Creates the proxy that represents a <code>DIRECT</code> connection.
    private Proxy() {
        type = type.DIRECT;
    }

    /**
     * Creates an entry representing a PROXY connection.
     * Certain combinations are illegal. For instance, for types Http, and
     * Socks, a SocketAddress <b>must</b> be provided.
     * <P>
     * Use the <code>Proxy.NO_PROXY</code> constant
     * for representing a direct connection.
     *
     * @param type the <code>Type</code> of the proxy
     * @throws IllegalArgumentException when the type and the address are
     * incompatible
     */
    public Proxy(Type type, String host, Integer port, String user, String password) {

        if ((type == Type.DIRECT)) {
            throw new IllegalArgumentException("type " + type + " is not compatible with address");
        }
        this.type = type;

        this.type = type;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    /**
     * Returns the proxy type.
     *
     * @return a Type representing the proxy type
     */
    public Type type() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Proxy getNoProxy() {
        return NO_PROXY;
    }

    /**
     * Constructs a string representation of this Proxy.
     * This String is constructed by calling toString() on its type
     * and concatenating " @ " and the toString() result from its address
     * if its type is not <code>DIRECT</code>.
     *
     * @return  a string representation of this object.
     */
    public String toString() {
        if (type() == Type.DIRECT)
            return "DIRECT";
        return type() + " @ " + getHost() + ":" + port;
    }
}