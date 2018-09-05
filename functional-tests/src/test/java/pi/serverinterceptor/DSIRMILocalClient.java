/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package pi.serverinterceptor;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import com.sun.corba.ee.impl.corba.AnyImpl;
import com.sun.corba.ee.spi.misc.ORBConstants;
import com.sun.corba.ee.impl.interceptors.*;
import org.omg.PortableInterceptor.*;
import org.omg.IOP.*;
import org.omg.IOP.CodecPackage.*;
import org.omg.IOP.CodecFactoryPackage.*;
import corba.framework.*;

import java.util.*;
import java.io.*;
import java.rmi.*;
import javax.naming.*;
import javax.rmi.*;

import ServerRequestInterceptor.*;

public class DSIRMILocalClient 
    extends DSIRMIClient
{
    public DSIRMILocalClient( com.sun.corba.ee.spi.orb.ORB orb ) {
        this.orb = orb;
    }

    public void run( Properties environment, String args[], PrintStream out,
                     PrintStream err, Hashtable extra) 
        throws Exception
    {
        out.println( "===============================================" );
        out.println( "Instantiating Client ORB for DSI RMI Local test" );
        out.println( "===============================================" );

        super.run( environment, args, out, err, extra );
    }

}

