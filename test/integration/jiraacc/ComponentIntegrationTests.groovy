package jiraacc

import static org.junit.Assert.*

import java.util.jar.Attributes.Name;

import org.junit.*

class ComponentIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void CanSaveAComponent() {
        def comp = new Component(Name:"test component")
		comp.save()
    }
	
	@Test
	void ComponentNameCanNotBeNull() {
		def comp = new Component()
		assertFalse("A null Component name should not be valid", comp.validate())
		assertTrue("A null Component name should cause an error", comp.hasErrors())
		
	}
}
