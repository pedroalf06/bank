package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

class TestUsuario {

	private final static Logger log = LoggerFactory.getLogger(TestUsuario.class);
	static EntityManagerFactory  entityManagerFactory = null;
	static EntityManager entityManager = null;
	
	private String usuarioId = new String("eynerdh");

	/*
	 * Metodos de JUnit
	 * BeforeAll: antes de ejecutar todos los metodos test
	 * AfterAll: despues de ejecutar todos los metodos de test
	 * BeforeEach: antes de ejecutar cada metodo
	 * AfterEach: despues de ejecutar cada metodo
	 */
	@BeforeAll
	public static void iniciar() {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-logica");
		entityManager = entityManagerFactory.createEntityManager();
		
		log.info("Ejecuto el BeforeAll");
	}
	
	@AfterAll
	public static void finalizar() {
		
		entityManager.close();
		entityManagerFactory.close();
		log.info("Ejecuto el AfterAll");
	}
	
	@BeforeEach
	public void antesB() {
		log.info("Se ejecuto antes de cada prueba");
	}
	
	@AfterEach
	public void despuesB() {
		log.info("Se ejecuto despues de cada prueba");
	}
	
	@Test
	@DisplayName("Crear Usuario")
	void atest() {
		
		assertNotNull(entityManager,"El entitymanager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNull(usuario, "El usuario ya existe");
		
		//el usuario no existe
		usuario = new Usuario();
		usuario.setUsuUsuario("eynerdh");
		usuario.setIdentificacion(new BigDecimal(6382278));
		usuario.setNombre("Eyner Delgado");
		usuario.setClave("eyner123*");
		usuario.setActivo('S');
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 3L);		
		assertNotNull(tipoUsuario,"El tipo de usuario no existe");
		usuario.setTipoUsuario(tipoUsuario);			
		
		entityManager.getTransaction().begin();
			entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
		log.info("Se ejecuto la prueba A: creacion del cliente");
		
	}	
	
	@Test
	void btest() {
		
		assertNotNull(entityManager,"El entitymanager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNotNull(usuario, "El usuario no existe");
		
		
		log.info("usuario: " + usuario.getUsuUsuario() );
		log.info("nombre usuario: " + usuario.getUsuUsuario());
		
	}
	
	@Test
	@DisplayName("Modificar Usuario")
	void ctest() {
		
		assertNotNull(entityManager,"El entitymanager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);		
		assertNotNull(usuario, "El usuario no existe");
		
		usuario.setUsuUsuario("eynerdh");
		usuario.setIdentificacion(new BigDecimal(6382278));
		usuario.setNombre("Eyner Delgado");
		usuario.setClave("P3P1T0");
		usuario.setActivo('N');
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 2L);		
		assertNotNull(tipoUsuario,"El tipo de usuario no existe");
		usuario.setTipoUsuario(tipoUsuario);			
		
		entityManager.getTransaction().begin();
			entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		
		log.info("Se ejecuto la prueba C: modificacion del usuario");
		
	}	
	
	@Test
	@DisplayName("Eliminar Usuario")
	void dtest() {
		
		assertNotNull(entityManager,"El entitymanager es nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);		
		assertNotNull(usuario, "El usuario no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		
		log.info("Se ejecuto la prueba D: eliminar usuario");
		
	}			
	
	@Test
	@DisplayName("Consultar Usuario")
	void etest() {
		
		assertNotNull(entityManager,"El entitymanager es nulo");
		
		String jpql = "SELECT usu FROM Usuario usu";
		
		List<Usuario> lstUsuarios = entityManager.createQuery(jpql).getResultList();
				
		lstUsuarios.forEach(usuario -> {
			log.info("ID: " + usuario.getUsuUsuario());
			log.info("NOMBRE:" + usuario.getNombre());			
		});
		
		log.info("Se ejecuto la prueba E: listar usuarios");
		
	}			

}
