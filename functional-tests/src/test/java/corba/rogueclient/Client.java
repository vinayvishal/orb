/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package corba.rogueclient;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.rmi.PortableRemoteObject;
import javax.naming.InitialContext;

import com.sun.corba.ee.spi.misc.ORBConstants;
import corba.hcks.U;

public class Client extends Thread
{
    private final static int NUMBER_OF_CLIENTS = 6;
    private final static boolean dprint = false;
    private final static int stringSize = 131072;
    private final static int TEST_SIZE = 50;
    private final static String stringOf36 = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static String reallyReallyBigString = null;
    private static String tmpString = null;

    private static void initializeReallyBigString() {
        StringBuffer sb = new StringBuffer(stringSize);
        int index = 0;
        final int lengthOfStr = stringOf36.length();
        for (int i = 0; i < stringSize; i++) {
            index = i % lengthOfStr;
            sb.append(stringOf36.charAt(index));
        }
        reallyReallyBigString = sb.toString();
    }

    private void runTest(Tester tester, int iterations)
        throws RemoteException {
        for (int i = 0; i < iterations; i++) {
            tmpString = tester.passString(reallyReallyBigString);
        }
    }

    public void run() {
        try {
            U.sop("Finding Tester ...");
            InitialContext rootContext = new InitialContext();
            U.sop("Looking up Tester...");
            java.lang.Object tst = rootContext.lookup("Tester");
            U.sop("Narrowing...");
            Tester tester
                    = (Tester)PortableRemoteObject.narrow(tst,
                    Tester.class);
            runTest(tester, TEST_SIZE);
        } catch (Throwable t) {
            U.sop("Unexpected throwable...");
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        if (dprint) {
            Properties props = new Properties();
            props.put(ORBConstants.DEBUG_PROPERTY, "transport, giop");
        }

        initializeReallyBigString();

        Client[] clients = new Client[NUMBER_OF_CLIENTS];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client();
        }

        for (int i = 0; i < clients.length; i++) {
            U.sop("Beginning client[" + i + "] test...");
            clients[i].start();
        }

        for (int i = 0; i < clients.length; i++) {
            try {
                clients[i].join();
                U.sop("Client[" + i + "] test finished successfully...");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

