package co.edu.usbcali.banco.logica;

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

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestClienteLogica {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	BigDecimal clieId = new BigDecimal(12200012);

	@Test
	@DisplayName("Crear Cliente")	
	void aTest()  throws Exception{
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente=new Cliente();
		
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(1L);
		assertNotNull(tipoDocumento,"EL tipo de documentos no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.grabar(cliente);
		
	}

	@Test
	@DisplayName("Modificar Cliente")	
	void bTest() throws Exception{
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente= clienteLogica.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente que desea modificar no existe");
		
		cliente.setActivo('N');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("555 555 6666");
		
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(2L);
		assertNotNull(tipoDocumento,"EL tipo de documentos no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.modificar(cliente);		
		
	}
	
	@Test
	@DisplayName("Eliminar Cliente")	
	void cTest() throws Exception{
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente= clienteLogica.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente que desea eliminar no existe");
				
		clienteLogica.borrar(cliente);		
		
	}
	
	@Test
	@DisplayName("Consultar Cliente por ID")	
	void dTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		
		Cliente cliente= clienteLogica.consultarPorId(clieId);
		
		assertNotNull(cliente, "El cliente no existe");		
		
	}
	
	@Test
	@DisplayName("Consultar Todos")	
	void eTest() {
		
		//propagation=Propagation.REQUIRED si no viene una transaccion la crea, si viene se une a ella
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
				
 		List<Cliente> lstCliente = clienteLogica.consultarTodos();
		
		assertNotNull(lstCliente, "La lista es nula");		
		
	}	
	
}
