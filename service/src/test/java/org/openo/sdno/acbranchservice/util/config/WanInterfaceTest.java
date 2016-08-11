package org.openo.sdno.acbranchservice.util.config;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;

import mockit.Mock;
import mockit.MockUp;

public class WanInterfaceTest {

	@Test
	public void testGetConfig() throws ServiceException {
		new MockUp<Files>() {
			@Mock
			 public byte[] readAllBytes(Path path) throws IOException {
				
				return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();
				 
			 }
		};
		
		new MockUp<Paths>() {
			@Mock
			public Path get(String first, String... more) {
				return null;
			}
		};
		String result = WanInterface.getConfig("123");
		//assertEquals("yes", result);
	}
	
	@Test
	public void testGetConfigException() throws ServiceException {
		new MockUp<Files>() {
			@Mock
			 public byte[] readAllBytes(Path path) throws IOException {
				
				return "[{}]".getBytes();
				 
			 }
		};
		
		new MockUp<Paths>() {
			@Mock
			public Path get(String first, String... more) {
				return null;
			}
		};
		String result = WanInterface.getConfig("123");
		//assertEquals("yes", result);
	}
	
	@Test
	public void testGetConfigBranch() throws ServiceException {
		new MockUp<Files>() {
			@Mock
			 public byte[] readAllBytes(Path path) throws IOException {
				
				return "[{\"cfgkey\":\"123\",\"cfgvalue\":\"yes\"}]".getBytes();
				 
			 }
		};
		
		new MockUp<Paths>() {
			@Mock
			public Path get(String first, String... more) {
				return null;
			}
		};
		String result = WanInterface.getConfig("1233");
		//assertEquals("yes", result);
	}
	
	@Test(expected = Exception.class)
	public void testGetConfigIOException() throws ServiceException {
		new MockUp<Files>() {
			@Mock
			 public byte[] readAllBytes(Path path) throws IOException {
				
				throw new IOException();
				 
			 }
		};
		
		new MockUp<Paths>() {
			@Mock
			public Path get(String first, String... more) {
				return null;
			}
		};
		String result = WanInterface.getConfig("1233");
		//assertEquals("yes", result);
	}

}
