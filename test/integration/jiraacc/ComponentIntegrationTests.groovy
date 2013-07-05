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
		
		println "\nErrors:"
		println comp.errors ?: "no errors found"
		
		def badField = comp.errors.getFieldError('name')
		println "\nBadField:"
		println badField ?: "name wasn't a bad field"
		assertNotNull "Expecting to find an error on the name field", badField
		
	}
	
	@Test
	void ComponentNameMustBeUnique() {
		def comp = new Component(name:"comp1")
		comp.save()
		
		def comp2 = new Component(name:"comp1")
		
		assertFalse("A Component name should should be unique", comp2.validate())
		assertTrue("A non unique Component name should cause an error", comp2.hasErrors())
		
		println "\nErrors:"
		println comp2.errors ?: "no errors found"
		
		def badField = comp2.errors.getFieldError('name')
		println "\nBadField:"
		println badField ?: "name wasn't a bad field"
		assertNotNull "Expecting to find an error on the name field", badField
		
	}
}
