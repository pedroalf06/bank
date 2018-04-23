package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestClienteDAO {
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	BigDecimal clieId = new BigDecimal(12200012);

	@Test
	@DisplayName("Crear Cliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente=new Cliente();
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(1L);
		assertNotNull(tipoDocumento,"EL tipo de documentos no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.grabar(cliente);
		
	}

	@Test
	@DisplayName("Modificar Cliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente= clienteDAO.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente que desea modificar no existe");
		
		cliente.setActivo('N');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("555 555 6666");
		
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(2L);
		assertNotNull(tipoDocumento,"EL tipo de documentos no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.modificar(cliente);		
		
	}
	
	@Test
	@DisplayName("Eliminar Cliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente= clienteDAO.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente que desea eliminar no existe");
				
		clienteDAO.borrar(cliente);		
		
	}
	
	@Test
	@DisplayName("Consultar Cliente por ID")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void dTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		
		Cliente cliente= clienteDAO.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente no existe");		
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void eTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
				
 		List<Cliente> lstCliente = clienteDAO.consultarTodos();
		
		assertNotNull(lstCliente, "La lista es nula");		
		
	}	
	
}
