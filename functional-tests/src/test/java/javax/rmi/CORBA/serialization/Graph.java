/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 1998-1999 IBM Corp. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package javax.rmi.CORBA.serialization;

// Class from Sun


import java.io.*;

public class Graph implements Serializable {

    public final String CONSTANT_STRING = "This is a constant string.";
    public final int CONSTANT_INT = 10;
    public final BitSet CONSTANT_BITSET = new BitSet(32);
    private String _list = null;
    private Graph _next = null;
    private BitSet _bitset = null;
    transient String t_string = "This is a transient string.";
    transient int t_int = 1111;
    transient BitSet t_bitset = new BitSet(32);

    public Graph(String data, Graph next) {
        this._list = data;
        this._bitset = new BitSet(64);
        this._bitset.set(10);this._bitset.set(20);
        this._bitset.set(30);this._bitset.set(40);
        this._bitset.set(50);this._bitset.set(60);
        this._next = next;
    }

    public String data() {
        return this._list;
    }

    public Graph next() {
        return this._next;
    }

    public void next(Graph next) {
        this._next = next;
    }

    public boolean equals(Graph o) {
        try{
            Graph g = (Graph)o;
            return ((_list.equals(g._list)) && 
                    (_next.equals(g._next)) &&
                    (_bitset.equals(g._bitset)));
        }
        catch(Throwable t){
            return false;
        }
    }

    public String toString() {
        StringBuffer result = new StringBuffer("{ ");
        for(Graph list = this; list != null; list = list.next()) {
            result.append(list.data()).append(" ");
        }
        return result.append("}").toString();
    }

        
}
