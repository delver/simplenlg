/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is "Simplenlg".
 *
 * The Initial Developer of the Original Code is Ehud Reiter, Albert Gatt and Dave Westwater.
 * Portions created by Ehud Reiter, Albert Gatt and Dave Westwater are Copyright (C) 2010-11 The University of Aberdeen. All Rights Reserved.
 *
 * Contributor(s): Ehud Reiter, Albert Gatt, Dave Wewstwater,
 * Roman Kutlak, Margaret Mitchell, Saad Mahamood.
 */

package simplenlg.test.server;

import simplenlg.server.SimpleServer;
import simplenlg.server.SimpleClient;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for simplenlg server
 *
 * @author Roman Kutlak
 */
public class ServerTest extends TestCase {

    SimpleServer serverapp;
    
    @Before
    protected void setUp() {
        try {
            serverapp = new SimpleServer(50007);
            Thread server = new Thread(serverapp);
            server.setDaemon(true);
            server.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @After
    protected void tearDown() {
        serverapp.terminate();
    }
    
    @Test
    public void testServer() {
        assertNotNull(serverapp);
        
        String expected = "Put the piano and the drum into the truck.";
        
        SimpleClient client = new SimpleClient();
        
        String result = client.run("localhost", 50007);
        
        assertEquals(expected, result);
    }
}
